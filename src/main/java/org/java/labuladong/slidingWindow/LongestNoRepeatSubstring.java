package org.java.labuladong.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长无重复子串
 *
 * LeetCode 第 3 题，Longest Substring Without Repeating Characters
 */
public class LongestNoRepeatSubstring {

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s1));
        System.out.println(lengthOfLongestSubstring(s2));
        System.out.println(lengthOfLongestSubstring(s3));

    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        // 最长无重复子串的长度
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            window.put(c,window.getOrDefault(c, 0) + 1);
            // 判断左侧窗口是否要收缩
            // 大于1时，说明窗口中存在重复字符，此时需要删除字符
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                window.put(d, window.getOrDefault(d, 0) - 1);
            }
            // 在这里更新答案
            // 因为窗口收缩的 while 条件是存在重复元素，换句话说收缩完成后一定保证窗口中没有重复
            res = Math.max(res, right - left);
        }
        return res;
    }

}
