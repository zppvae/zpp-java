package org.java.labuladong.binaryTree;

import org.java.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 查找重复子树
 *
 * 力扣第 652 题「寻找重复子树」
 *
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487527&idx=1&sn=9cf2b0d8608ba26ea7c6a5c9b41d05a1&chksm=9bd7ee2faca0673916bf075539bf6fc3c01f3dcc0b298b3f507047692ef5c850ed9cfe82e4e6&scene=21#wechat_redirect
 */
public class FindDuplicateSubtrees {

    // 记录所有子树以及出现的次数
    public static HashMap<String, Integer> memo = new HashMap<>();
    // 记录重复的子树根节点
    public static LinkedList<TreeNode> res = new LinkedList<>();

    public static void main(String[] args) {

    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    public static String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right+ "," + root.val;

        int freq = memo.getOrDefault(subTree, 0);
        // 多次重复也只会被加入结果集一次
        if (freq == 1) {
            // 有人和我重复
            res.add(root);
        }
        // 给子树对应的出现次数加一
        memo.put(subTree, freq + 1);
        return subTree;
    }


}
