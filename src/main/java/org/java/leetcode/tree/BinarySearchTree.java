package org.java.leetcode.tree;

/**
 * 二叉搜索树
 *
 * 题目：给定一个二叉搜索树(BST)，找到树中第 K 小的节点
 *
 * 树相关的题目，第一眼就想到递归求解，左右子树分别遍历。
 * 联想到二叉搜索树的性质，root 大于左子树，小于右子树，如果左子树
 * 的节点数目等于 K-1，那么 root 就是结果，否则如果左子树节点数
 * 目小于 K-1，那么结果必然在右子树，否则就在左子树。因此在搜
 * 索的时候同时返回节点数目，跟 K 做对比，就能得出结果了。
 *
 *
 *                          5
 *                         /  \
 *                        3    6
 *                       / \
 *                      2   4
 *                     /
 *                    1
 *
 *  输入K = 3，输出节点值 = 3
 *
 */
public class BinarySearchTree {
    private class ResultType {
        // 是否找到
        boolean found;
        // 节点数目
        int val;

        ResultType(boolean found, int val) {
            this.found = found;
            this.val = val;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        return kthSmallestHelper(root, k).val;
    }

    private ResultType kthSmallestHelper(TreeNode root, int k) {

        if (root == null) {
            return new ResultType(false, 0);
        }
        ResultType left = kthSmallestHelper(root.left, k);
        // 左子树找到，直接返回
        if (left.found) {
            return new ResultType(true, left.val);
        }
        // 左子树的节点数目 = K-1，结果为 root 的值
        if (k - left.val == 1) {
            return new ResultType(true, root.val);
        }
        // 右子树寻找
        ResultType right = kthSmallestHelper(root.right, k - left.val - 1);
        if (right.found) {
            return new ResultType(true, right.val);
        }
        // 没找到，返回节点总数
        return new ResultType(false, left.val + 1 + right.val);
    }
}