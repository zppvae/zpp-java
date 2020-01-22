package org.java.leetcode.array;

/**
 * 给定一个长度为 n+1 的数组 nums，数组中所有的数均在 1∼n 的范围内，其中 n≥1。
 *
 * 请找出数组中任意一个重复的数，但不能修改输入的数组。
 *
 * 样例
 *
 * 给定 nums = [2, 3, 5, 4, 3, 2, 6, 7]。
 *
 * 返回 2 或 3。
 *
 *
 * @author zpp
 * @date 2019/9/20 11:29
 */
public class NoModifyArrayFindRepeatNumber {

    public static void main(String[] args){
        int[] nums = {2, 3, 5, 4, 3, 2, 6, 7};
        System.out.println(method1(nums));
    }

    /**
     * 创建长度为 n+1 的辅助数组，把原数组的元素复制到辅助数组中。
     * 如果原数组被复制的数是 m，则放到辅助数组第 m 个位置。这样很容易找出重复元素。空间复杂度为 O(n)。
     *
     *
     * @param nums
     * @return
     */
    public static int method1(int[] nums){
        int length = nums.length;
        if (nums == null || length == 0) {
            return 0;
        }
        int[] copyArr = new int[length];

        for (int i = 0; i < length; i++) {
            int tmp = nums[i];
            if (copyArr[tmp] == 0) {
                copyArr[tmp] = tmp;
            } else {
                return tmp;
            }
        }
        return 0;
    }
}
