package org.java.labuladong.array;

import java.util.Arrays;

/**
 * 将数组中的元素 0 移动到数组的末尾
 *
 * 力扣第 283 题
 *
 */
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 2, 3, 2, 0};
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }

    }

    /**
     * 去除 nums 中的所有 0
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        // 将 slow 之后的所有元素赋值为 0
        for (; slow < nums.length; slow++) {
            nums[slow] = 0;
        }
    }
}
