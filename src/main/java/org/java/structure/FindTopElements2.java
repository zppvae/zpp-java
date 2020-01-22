package org.java.structure;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
/**
 * 
 * 使用位图数组存储元素，一个int占用4个字节，32个bit，也就是说1个int可以表示32个数字的位置。 维护一个数组长度/32+1的位图数组x，遍历给定的数组，将数字安插进入这个位图数组x中，例如int[0]=62，那么

	index=62/32=1
	
	bit_index=62 mod 32 = 30
	
	那么就置位图数组的x[1]=x[1] | 30，采用“位或”是为了不丢掉以前处理过的数字。
 * @author zhangxu04
 */
public class FindTopElements2 {
 
	private static final int ARRAY_LENGTH = 10000000; // big array length
 
	public static void main(String[] args) {
		FindTopElements2 fte = new FindTopElements2(ARRAY_LENGTH + 1);
 
		// Get a array which is not in order and elements are not duplicate
		int[] array = getShuffledArray(ARRAY_LENGTH);
 
		// Find top 100 elements and print them by desc order in the console
		long start = System.currentTimeMillis();
		fte.findTop100(array);
		long end = System.currentTimeMillis();
		System.out.println("Costs " + (end - start) + "ms");
	}
 
	private final int[] bitmap;
 
	private final int size;
 
	public FindTopElements2(final int size) {
		this.size = size;
		int len = ((size % 32) == 0) ? size / 32 : size / 32 + 1;
		this.bitmap = new int[len];
	}
 
	private static int index(final int number) {
		return number / 32;
	}
 
	private static int position(final int number) {
		return number % 32;
	}
 
	private void adjustBitMap(final int index, final int position) {
		int bit = bitmap[index] | (1 << position);
		bitmap[index] = bit;
	}
 
	public void add(int[] numArr) {
		for (int i = 0; i < numArr.length; i++) {
			add(numArr[i]);
		}
	}
 
	public void add(int number) {
		adjustBitMap(index(number), position(number));
	}
 
	public boolean getIndex(final int index) {
		if (index > size) {
			return false;
		}
 
		int bit = (bitmap[index(index)] >> position(index)) & 0x0001;
		return (bit == 1);
	}
 
	private void findTop100(int[] arr) {
		System.out.println("Start to compute.");
		add(arr);
		int[] result = new int[100];
		int index = 0;
		for (int i = bitmap.length - 1; i >= 0; i--) {
			for (int j = 31; j >= 0; j--) {
				if (((bitmap[i] >> j) & 0x0001) == 1) {
					if (index == result.length) {
						break;
					}
					result[index++] = ((i) * 32) + j ;
				}
			}
			if (index == result.length) {
				break;
			}
		}
 
		for (int j = 0; j < result.length; j++) {
			System.out.println(result[j]);
		}
		System.out.println("Finish to output result.");
	}
 
	/**
	 * Get shuffled int array
	 * 
	 * @return array not in order and elements are not duplicate
	 */
	private static int[] getShuffledArray(int len) {
		System.out.println("Start to generate test array... this may take several seconds.");
		List<Integer> list = new ArrayList<Integer>(len);
		for (int i = 0; i < len; i++) {
			list.add(i);
		}
		Collections.shuffle(list);
 
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}
 
}