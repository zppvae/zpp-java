package org.java.labuladong.BFS;

import org.java.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 二叉树的最小深度
 */
public class TreeMinDepth {

    public static void main(String[] args) {
        // [3, 9, 20, null, null, 15, 7]
        TreeNode root = new TreeNode(3);
        TreeNode rootLeft = new TreeNode(9);
        TreeNode rootRight = new TreeNode(20);
        root.left = rootLeft;
        root.right = rootRight;

        rootRight.left = new TreeNode(15);
        rootRight.right = new TreeNode(7);

        System.out.println(minDepth(root));
    }

    /**
     * 求最小深度
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 核心数据结构
        Queue<TreeNode> q = new LinkedList<>();
        // 将起点加入队列
        q.offer(root);
        // root 本身就是一层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            // 将当前队列中的所有节点向四周扩散
            for (int i = 0; i < sz; i++) {
                // 出队列
                TreeNode cur = q.poll();
                // 判断是否到达终点，即叶子节点
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                // 将 cur 的相邻节点加入队列
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            // 这里增加步数
            depth++;
        }
        return depth;
    }

}
