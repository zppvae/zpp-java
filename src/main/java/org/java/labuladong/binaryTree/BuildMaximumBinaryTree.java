package org.java.labuladong.binaryTree;

import org.java.leetcode.tree.TreeNode;

/**
 * 构造最大二叉树
 *
 * 力扣第 654 题
 *
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487270&idx=1&sn=2f7ad74aabc88b53d94012ceccbe51be&chksm=9bd7f12eaca078384733168971147866c140496cb257946f8170f05e46d16099f3eef98d39d9&scene=21#wechat_redirect
 */
public class BuildMaximumBinaryTree {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 6, 0, 5};
        System.out.println(constructMaximumBinaryTree(nums).getVal());
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    /**
     * 将 nums[lo..hi] 构造成符合条件的树，返回根节点
     *
     * @param nums
     * @param lo
     * @param hi
     * @return
     */
    public static TreeNode build(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }
        // 找到数组中的最大值和对应的索引
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxVal);
        // 递归调用构造左右子树
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }

}
