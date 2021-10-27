package org.java.labuladong.array;

/**
 *
 * 移除元素
 *
 * 力扣第 27 题
 *
 */
public class RemoveElement {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 1, 1, 2, 2, 3, 4, 4};
        System.out.println(removeElement(nums, 1));
    }

    /**
     * 如果fast遇到需要去除的元素，则直接跳过，否则就告诉slow指针，并让slow前进一步。
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

}
