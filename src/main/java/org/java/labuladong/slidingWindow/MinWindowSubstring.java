package org.java.labuladong.slidingWindow;

import java.util.HashMap;

/**
 *
 * LeetCode 76 题，最小覆盖子串
 *
 */
public class MinWindowSubstring {

    public static void main(String[] args) {
        String s = "EBBANCF";
        String t = "EABBC";
        System.out.println(minWindow(s, t));
    }

    public static String minWindow(String s, String t) {
        // 窗口的左边界、右边界
        int left = 0, right = 0;
        // 记录窗口中符合字符的个数
        int valid = 0;
        // 记录最小覆盖字串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        // 窗口里符合的字符
        HashMap<Character,Integer> window = new HashMap<>();
        // 需要的字符
        HashMap<Character,Integer> needs = new HashMap<>();
        // 记录t中字符出现的次数
        for (char c : t.toCharArray()) {
            needs.put(c,needs.getOrDefault(c, 0) + 1);
        }
        while (right < s.length()) {
            // 扩大窗口
            char c = s.charAt(right);
            right++;
            // 更新窗口的数据
            if (needs.containsKey(c)) {
                // 若当前字符是满足条件的，则更新窗口window中字符出现的次数，
                window.put(c, window.getOrDefault(c, 0) + 1);
                // 若遇到了存在的字符则窗口中字符的总次数加一
                if (window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            // 满足条件（出现了需要的全部的字符即valid满足了），则缩小窗口
            while (valid == needs.size()) {
                // 计算窗口的长度。比较当前窗口的长度和上一个窗口的最小长度，若当前窗口的长度更小则更新最小长度，否则不更新，
                if (right - left < len) {
                    // 窗口开始的下标，
                    start = left;
                    len = right - left;
                }
                // 开始缩小窗口，lChar 是移出窗口的字符
                char lChar = s.charAt(left);
                left++;

                // 更新窗口内的值
                // 若左边的字符是needs中的，则窗口的window中字符出现的次数减一，因为要移除左边的字符
                if (needs.containsKey(lChar)) {
                    if (needs.get(lChar).equals(window.get(lChar))){
                        valid--;
                    }
                    // 移除窗口中window的字符出现次数
                    window.put(lChar,window.getOrDefault(lChar, 0) - 1);
                }
            }
        }
        // len 判断是否存在，不存在返回空，存在则截取s，返回最小覆盖子串
        return len == Integer.MAX_VALUE ? "": s.substring(start, start + len);
    }

}
