package org.java.labuladong.BFS;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * LeetCode 题目是第 752 题
 *
 * 打开转盘锁
 *
 */
public class PasswordLock {

    public static void main(String[] args) {
        // 记录需要跳过的死亡密码
        Set<String> deads = Sets.newHashSet("0201", "0101", "0102", "1212", "2002");
        String target = "0202";
        System.out.println(openLock(deads, target));
    }

    /**
     * 将 s[j] 向上拨动一次
     *
     * @param s
     * @param j
     * @return
     */
    public static String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9') {
            ch[j] = '0';
        } else {
            ch[j] += 1;
        }
        return new String(ch);
    }

    /**
     * 将 s[i] 向下拨动一次
     *
     * @param s
     * @param j
     * @return
     */
    public static String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0') {
            ch[j] = '9';
        } else {
            ch[j] -= 1;
        }
        return new String(ch);
    }

    /**
     * 打开密码锁
     *
     * @param deads
     *         死亡密码
     * @param target
     * @return
     */
    public static int openLock(Set<String> deads, String target) {
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            // 将当前队列中的所有节点向周围扩散
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                // 判断是否到达终点
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }
                // 将一个节点的未遍历相邻节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            // 在这里增加步数
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }
}
