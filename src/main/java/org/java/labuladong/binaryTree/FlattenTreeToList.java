package org.java.labuladong.binaryTree;

import org.java.leetcode.tree.TreeNode;

/**
 * 将二叉树展开为链表
 *
 * 力扣第 114 题
 *
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487126&idx=1&sn=4de13e66397bc35970963c5a1330ce18&chksm=9bd7f09eaca0798853c41fba05ad5fa958b31054eba18b69c785ae92f4bd8e4cc7a2179d7838&scene=21#wechat_redirect
 *
 */
public class FlattenTreeToList {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(6);
        root.left = left;
        root.right = right;

        flatten(root);
    }

    /**
     * 定义：将以 root 为根的树拉平为链表
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        // base case
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);

        /**** 后序遍历位置 ****/
        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }
}
