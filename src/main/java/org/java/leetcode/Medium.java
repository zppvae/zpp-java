package org.java.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 中等难度
 * Description:
 * @author zpp
 * @date   2018年5月14日
 */
public class Medium {
	
	public static void main(String[] args) {
//		System.out.println(lengthOfLongestSubstring("abcabcbb"));
		
		System.out.println(compareVersion("7.5.5.2", "7.5.5"));
	}
	
	/**
	 * 给定一个字符串，找出不含有重复字符的最长子串的长度
	 * https://blog.csdn.net/qq_28618765/article/details/65627503
	 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s) {
		//记录字符最近出现的位置
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		// map中的键key存放字符串中出现的字符，值value存放该字符当前的位置
		int maxLength = 0; // 保存最长字串长度
		int now = 0; // 记录头指针位置

		for (int i = 0; i < s.length(); i++) {
			// 如果map中已存在当前字符
			if (map.containsKey(s.charAt(i))) {
				// 更新当前指针位置，如果当前指针大，则使用当前指针，否则使用该指针下一个字符的位置
				now = Math.max(now, map.get(s.charAt(i)) + 1);
			} 
			if ((i - now + 1) > maxLength) { // 更新最长字串的长度
				maxLength = i - now + 1;
			}
			map.put(s.charAt(i), i);// 修改当前字符的value，记录最新位置
		}
		return maxLength;
	}
	
	/**
	 * 版本号比较
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion(String version1, String version2) {
		String[] arr1 = version1.split("[.]");
        String[] arr2 = version2.split("[.]");

        int length = Math.max(arr1.length, arr2.length);

        for(int i = 0; i < length; i++) {
            Integer a1 = i < arr1.length ? Integer.parseInt(arr1[i]) : 0;
            Integer a2 = i < arr2.length ? Integer.parseInt(arr2[i]) : 0;

            int compare = a1.compareTo(a2);
            if(compare != 0) return compare;
        }
        return 0;  
    }
	
	
}