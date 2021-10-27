package org.java.labuladong.nsum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * twoSum 问题
 *
 *
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {1,3,1,2,2,3};
        System.out.println(twoSumTarget(nums, 4));
    }

    public static List<List<Integer>> twoSumTarget(int[] nums, int target) {
        // nums 数组必须有序
        Arrays.sort(nums);
        // 左右指针
        int lo = 0, hi = nums.length - 1;
        // 保存结果数据
        List<List<Integer>> res = new LinkedList<>();
        while (lo < hi) {
            // 左右元素的和
            int sum = nums[lo] + nums[hi];
            // 左右元素临时变量
            int left = nums[lo], right = nums[hi];
            // 和 < 目标值
            if (sum < target) {
                // 向右移动，且过滤掉左侧重复数据
                while (lo < hi && nums[lo] == left) {
                    lo++;
                }
            } else if (sum > target) {
                // 向左移动，且过滤掉右侧的重复数据
                while (lo < hi && nums[hi] == right) {
                    hi--;
                }
            } else {
                // 相等，添加到结果集中
                res.add(Arrays.asList(left, right));
                while (lo < hi && nums[lo] == left) {
                    lo++;
                }
                while (lo < hi && nums[hi] == right) {
                    hi--;
                }
            }
        }
        return res;
    }

}
