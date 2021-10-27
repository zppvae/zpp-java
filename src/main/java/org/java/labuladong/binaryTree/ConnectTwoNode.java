package org.java.labuladong.binaryTree;

import com.alibaba.fastjson.JSON;
import org.java.leetcode.tree.TreeNode;

/**
 * 连接二叉树节点的右侧指针
 *
 * 力扣第 116 题
 *
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487126&idx=1&sn=4de13e66397bc35970963c5a1330ce18&chksm=9bd7f09eaca0798853c41fba05ad5fa958b31054eba18b69c785ae92f4bd8e4cc7a2179d7838&scene=21#wechat_redirect
 *
 */
public class ConnectTwoNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        left.left = new TreeNode(4);
        left.right = new TreeNode(5);

        right.left = new TreeNode(6);
        right.right = new TreeNode(7);

        // 5 的下个节点是 6
        System.out.println(connect(root).getLeft().getRight().next.getVal());
    }

    public static TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }

    /**
     * 定义：输入两个节点，将它俩连接起来
     *
     * @param node1
     * @param node2
     */
    public static void connectTwoNode(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序遍历位置 ****/
        // 将传入的两个节点连接
        node1.next = node2;

        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }
}
