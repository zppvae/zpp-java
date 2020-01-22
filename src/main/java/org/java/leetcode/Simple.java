package org.java.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * @author zpp
 * @date   2018年5月14日
 */
public class Simple {
	
	public static void main(String[] args) {
//		int[] nums = {2, 7, 11, 15};
//		int[] result = twoSum(nums,17);
//		System.out.println(Arrays.toString(result));
		
//		int[] nums = {0,1,2,2,3,0,4,2};
//		int val = 2;
//		int length = removeElement(nums, val);
//		System.out.println(Arrays.toString(nums));
		
//		int[] nums = {0,0,1,1,1,2,2,3,3,4};
//		removeDuplicates(nums);
//		System.out.println(Arrays.toString(nums));
		
//		System.out.println(strStr("aaaaa", "bba"));
		
//		int[] nums = {1,3,5,6};
//		System.out.println(searchInsert(nums, 7));
		
		int[] nums = {9,2,9};
		System.out.println(Arrays.toString(plusOne(nums)));
	}
	
	/**
	 * * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数
	 * 给定 nums = [2, 7, 11, 15], target = 9
		因为 nums[0] + nums[1] = 2 + 7 = 9
		所以返回 [0, 1]
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		if (nums == null || nums.length < 2) {
			return null;
		}
		Map<Integer,Integer> map = new HashMap<>();
		for (int i = 0;i<nums.length;i++) {
			int m = target - nums[i];
			
			if (map.containsKey(nums[i])) {
				result[0] = map.get(nums[i]);
				result[1] = i;
				
				break;
			}
			
			map.put(m, i);
		}
		return result;
	}
	
	/**
	 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
		示例 1:
			输入: 123
			输出: 321
		示例 2:
			输入: -123
			输出: -321
		示例 3:
			输入: 120
			输出: 21
	 * @param x
	 * @return
	 */
	public static int reverse(int x) {
		int n = Math.abs(x);
        char[] chars = String.valueOf(n).toCharArray();
        
        StringBuilder sb = new StringBuilder();
        for (int i = chars.length - 1; i >=0 ;i--) {
        	sb.append(chars[i]);
        }
        
        try {
            Integer result = Integer.valueOf(sb.toString());
            return x < 0 ? -result : result;
        } catch (NumberFormatException e) {
            return 0;
        }

    }
	
	/**
	 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
	 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
	 * 
	 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
	 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
	 * @param nums
	 * @param val
	 * @return
	 */
	public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        int count = 0;
        for(int i=0;i<nums.length;i++){
        	if (nums[i] != val) {
        		nums[count++] = nums[i];
        	}
        }
        return count;
    }
	
	/**
	 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	 * 
	 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
	 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
	 * @param nums
	 * @return
	 */
	public static int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int count = 0;
		Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
        	if (!map.containsKey(nums[i])) {
        		nums[count++] = nums[i];
        	}
        	map.put(nums[i], i);
        }
        
        return count;
    }
	
	/**
	 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public static int strStr(String haystack, String needle) {
        if (haystack == "" || haystack == null) {
        	throw new NullPointerException("haystack不能为空");
        }
        if (needle == "" || needle == null) {
        	return 0;
        }
       
		return haystack.indexOf(needle);
    }
	
	
	/**
	 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置
	 * 
	 * 输入: [1,3,5,6], 5     输出: 2
	 * 输入: [1,3,5,6], 0     输出: 0
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int searchInsert(int[] nums, int target) {
        
		for(int i=0;i<nums.length;i++){
			if (nums[i] == target) {
				return i;
			}
		}
		for (int i = 0;i < nums.length;i++) {
			
			if (target > nums[i] && (((i+1) < nums.length && target < nums[i+1])
					|| ((i+1) >= nums.length))) {
				return i+1;
			}
			
		}
		return 0;
    }  
	
	/**
	 * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
	 * 
	 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
	 * 
	 * 输入: [4,3,2,1]  输出: [4,3,2,2]
	 * @param digits
	 * @return
	 */
	public static int[] plusOne(int[] digits) {
		//+1标志
		int carry = 1;
        for(int i=digits.length-1; i>=0; i--) {
            if(carry==0) {
                return digits;
            }
            int tmp = digits[i] + carry;
            carry = tmp / 10;
            digits[i] = tmp % 10;
        }
        if(carry!=0) {
            int[] res = new int[digits.length+1];
            res[0] = 1;
            return res;
        }
        return digits;
	}
}