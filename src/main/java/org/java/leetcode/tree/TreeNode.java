package org.java.leetcode.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 定义二叉树
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode next;

    public TreeNode(int x) {
        val = x;
    }

    public static String traverse(TreeNode root) {
        // 对于空节点，可以用一个特殊字符表示
        if (root == null) {
            return "#";
        }
        // 将左右子树序列化成字符串
        String left = traverse(root.left);
        String right = traverse(root.right);
        /* 后序遍历代码位置 */
        // 左右子树加上自己，就是以自己为根的二叉树序列化结果
        String subTree = left + "," + right + "," + root.val;
        return subTree;
    }
}