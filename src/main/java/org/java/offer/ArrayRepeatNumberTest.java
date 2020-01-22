package org.java.offer;

import org.java.util.IntegerUtils;

/**
 * 数组中重复数字
 *
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
 * 那么对应的输出是第一个重复的数字2。
 *
 * 这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素放到第 i 个位置上
 *
 * 要求复杂度为 O(N) + O(1)，也就是时间复杂度 O(N)，空间复杂度 O(1)
 *
 * Description:
 * @author zpp
 * @date   2018年4月27日
 */
public class ArrayRepeatNumberTest {
	
	public static void main(Strings[] args) throws Exception {
		int[] numbers = {4,3,1,0,2,5,3};

//		duplicate0(numbers, numbers.length, new int[1]);
	}

	/**
	 * 时间、空间都满足要求
	 * @param numbers
	 * @param length
	 * @param duplication
	 * @return
	 */
	public static boolean duplicate0(int numbers[], int length, int[] duplication) {
		if (numbers == null || length <=0) {
			return false;
		}

		for (int i = 0; i < length; i++) {
			//第i个位置的元素 != 第i个位置
			while (numbers[i] != i) {
				if (numbers[i] == numbers[numbers[i]]) {
					duplication[0] = numbers[i];
					System.out.println(numbers[i]);
					return true;
				}
				IntegerUtils.swap(numbers, i, numbers[i]);
			}
		}
		return false;
	}
	
	/**
	 * 时间复杂度不符合
	 * @param numbers
	 * @param length
	 * @param duplication  
	 * 			返回任意重复的一个，赋值duplication[0]
	 * @return
	 */
	public static boolean duplicate(int numbers[], int length, int[] duplication) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(numbers[i] + "");
		}
		for (int j = 0; j < length; j++) {
			int a = sb.indexOf(numbers[j] + "");
			int b = sb.lastIndexOf(numbers[j] + "");
			if (a != b) {
				duplication[0] = numbers[j];
				System.out.println(duplication[0]);
				return true;
			}
		}
		return false;
	}
	
	//boolean只占一位
    public static boolean duplicate2(int numbers[], int length, int[] duplication) {
        boolean[] k = new boolean[length];
        for (int i = 0; i < k.length; i++) {
            if (k[numbers[i]] == true) {
                duplication[0] = numbers[i];
                System.out.println(duplication[0]);
                return true;
            }
            k[numbers[i]] = true;
        }
        return false;
    }


}
