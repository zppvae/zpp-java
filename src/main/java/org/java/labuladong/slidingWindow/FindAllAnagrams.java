package org.java.labuladong.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 找所有字母异位词
 *
 * 异位词也就是排列
 *
 * LeetCode 第 438 题，Find All Anagrams in a String
 */
public class FindAllAnagrams {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String t = "abc";
        System.out.println(findAnagrams(s, t));
    }

    public static Set<Integer> findAnagrams(String s, String t) {
        int left = 0, right = 0;
        // 窗口里符合的字符
        HashMap<Character,Integer> window = new HashMap<>();
        // 需要的字符
        HashMap<Character,Integer> needs = new HashMap<>();
        // 完全匹配时字符的个数
        int valid = 0;
        // 记录t中字符出现的次数
        for (char c : t.toCharArray()) {
            needs.put(c,needs.getOrDefault(c, 0) + 1);
        }

        Set<Integer> res = new HashSet<>();
        // 滑动
        while (right < s.length()) {
            // 开始扩大窗口
            char c = s.charAt(right);
            right++;

            // 符合条件则更新数据
            if (needs.containsKey(c)) {
                window.put(c,window.getOrDefault(c, 0) + 1);
                // 实际匹配的和目标一致时，表示当前字符匹配完成
                if (window.get(c).equals(needs.get(c))) {
                    valid++;
                }
            }
            // 缩小窗口的条件是窗口大于或等于t的长度时开始缩小，
            // 如t为ab，则boa、aob、axxxb都不符合排列要求，排列要求是两个子串紧挨着，即子串的长度
            while(right - left >= t.length()) {
                // 完全匹配
                if (valid == needs.size()) {
                    res.add(left);
                }
                // 开始缩小窗口
                char d = s.charAt(left);
                left++;
                // 缩小时判断并更新数据
                if (needs.containsKey(d)) {
                    // 窗口中包含需要的字符时，已匹配的计数值减一
                    if (window.get(d).equals(needs.get(d))) {
                        valid--;
                    }
                    // 更新窗口中的数据，让其值减一
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }
        return res;
    }
}
