package org.java.leetcode.array;

import org.java.util.IntegerUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 给定一个长度为 n 的整数数组 nums，数组中所有的数字都在 0∼n−1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 *
 * 注意：如果某些数字不在 0∼n−1 的范围内，或数组中不包含重复数字，则返回 -1
 *
 * 样例：
 *
 * 给定 nums = [2, 3, 5, 4, 3, 2, 6, 7]。
 *
 * 返回 2 或 3。
 *
 *
 * @author zpp
 * @date 2019/9/19 16:40
 */
public class FindArrayRepeatNumbers {

    public static void main(String[] args){
        int[] nums = {2, 3, 5, 4, 3, 2, 6, 7};
        System.out.println(method2(nums));
    }


    /**
     * 利用哈希表，遍历数组，如果哈希表中没有该元素，则存入哈希表中，否则返回重复的元素。
     *
     * 时间复杂度为 O(n)，空间复杂度为 O(n)。
     * @param nums
     * @return
     */
    public static int method1(int[] nums){
        int length = nums.length;
        Map<Integer,Integer> map = new HashMap<>(length);

        for (int i = 0; i < length; i++) {
            if (nums[i] < 0 || nums[i] >= length) {
                return -1;
            }
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], nums[i]);
            } else {
              return nums[i];
            }
        }

        return -1;
    }


    /**
     * 长度为 n，元素的数值范围也为 n，如果没有重复元素，那么数组每个下标对应的值与下标相等。
     *
     * 从头到尾遍历数组，当扫描到下标 i 的数字 nums[i]：
     *
     * 如果等于 i，继续向下扫描；
     * 如果不等于 i，拿它与第 nums[i] 个数进行比较，如果相等，说明有重复值，返回 nums[i]。如果不相等，就把第 i 个数 和第 nums[i] 个数交换。重复这个比较交换的过程。
     *
     * 此算法时间复杂度为 O(n)，因为每个元素最多只要两次交换，
     * 就能确定位置（比如把 2 跟 5 交换，此时 2 在正确的位置，而 5 需要再交换一次就能跑到正确的位置）。\
     * 空间复杂度为 O(1)。
     *
     * @param nums
     * @return
     */
    public static int method2(int[] nums){
        int length = nums.length;

        for (int a : nums){
            if (a < 0 || a >= length) {
                return -1;
            }
        }

        for (int i = 0; i < length; i++) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                IntegerUtils.swap(nums,i,nums[i]);
            }
        }

        return -1;
    }
}

