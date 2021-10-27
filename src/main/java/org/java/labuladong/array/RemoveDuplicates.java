package org.java.labuladong.array;

/**
 * 删除重复元素
 *
 * 力扣26、删除有序数组中的重复项
 *
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487466&idx=1&sn=e0c21cf8c3a76cfc4844b1269b658344&chksm=9bd7f1e2aca078f49d1a4090f80969bd4dc415fae6756e488e8b710965ea12baa4d3ac9b0d46&scene=21#wechat_redirect
 *
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 1, 1, 2, 2, 3, 4, 4};
        System.out.println(removeDuplicates(nums));
    }

    /**
     *
     * 让慢指针slow走在后面，快指针fast走在前面探路，找到一个不重复的元素就告诉slow并让slow前进一步。
     * 这样当fast指针遍历完整个数组nums后，nums[0..slow]就是不重复元素
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 数组长度为索引 + 1
        return slow + 1;
    }
}
