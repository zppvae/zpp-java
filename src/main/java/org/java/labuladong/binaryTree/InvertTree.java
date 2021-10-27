package org.java.labuladong.binaryTree;

import com.alibaba.fastjson.JSON;
import org.java.leetcode.tree.TreeNode;

/**
 * 翻转二叉树
 *
 * 力扣第 226 题「翻转二叉树」，输入一个二叉树根节点root，让你把整棵树镜像翻转
 *
 *  输入的二叉树：
 *       4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 翻转后的二叉树:
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 */
public class InvertTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(7);
        root.left = left;
        root.right = right;

        left.left = new TreeNode(1);
        left.right = new TreeNode(3);

        right.left = new TreeNode(6);
        right.right = new TreeNode(9);

        System.out.println(JSON.toJSONString(invertTree(root)));
    }

    /**
     * 把二叉树上的每一个节点的左右子节点进行交换，最后的结果就是完全翻转之后的二叉树
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        // base case
        if (root == null) {
            return null;
        }

        /**** 前序遍历位置 ****/
        // root 节点需要交换它的左右子节点
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        // 让左右子节点继续翻转它们的子节点
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
