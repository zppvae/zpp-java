package org.java.structure.recursion;


/**
 * 汉诺塔的递归算法
 * Description:
 * @author zpp
 * @date   2018年6月13日
 */
public class HanoiRecursion {

	/**
	 * 移动路径
	 * @param n
	 *            汉诺塔的层数
	 * @param x
	 *            x柱 起点柱
	 * @param y
	 *            y柱 目标柱
	 * @param z
	 *            z柱 中转柱
	 */
	public void hanoi(int n, char x, char y, char z) {

		// H(0)=0
		if (n == 0) {
			// 什么也不做
		} else {
			// H(n)=H(n-1) + 1 H(n-1)
			// 将n-1个圆盘从x移动到z,y为中转柱
			hanoi(n - 1, x, z, y); // ----------------------->解出n-1层汉诺塔:H(n-1)

			// 移动最大圆盘到目的柱
			System.out.println(x + "->" + y);// ------------> 1

			// 将n-1个圆盘从z移动到y,x为中转柱
			hanoi(n - 1, z, y, x);// ------------------------>解出n-1层汉诺塔:H(n-1)
		}

	}

	/**
	 * 
	 * 记录总次数
	 * @param n
	 *            汉诺塔的层数
	 * @param x
	 *            x柱 起点柱
	 * @param y
	 *            y柱 目标柱
	 * @param z
	 *            z柱 中转柱
	 */
	public int hanoiCount(int n, char x, char y, char z) {
		int moveCount = 0;
		// H(0)=0
		if (n == 0) {
			// 什么也不做
			return 0;
		} else {
			// H(n)=H(n-1) + 1 H(n-1)
			// 将n-1个圆盘从x移动到z,y为中转柱
			moveCount += hanoiCount(n - 1, x, z, y); // ------------->解出n-1层汉诺塔:H(n-1)

			// 移动最大圆盘到目的柱
			moveCount += 1; // ---------------------------------> 1

			// 将n-1个圆盘从z移动到y,x为中转柱
			moveCount += hanoiCount(n - 1, z, y, x);// --------------->解出n-1层汉诺塔:H(n-1)
		}

		return moveCount;
	}

	// 测试
	public static void main(String[] args) {
		HanoiRecursion hanoi = new HanoiRecursion();
		System.out.println("moveCount=" + hanoi.hanoiCount(6, 'A', 'B', 'C'));

		hanoi.hanoi(3, 'A', 'B', 'C');
	}

}