package org.java.labuladong.nsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * threeSum 问题
 *
 *
 */
public class ThreeSum {

    public static void main(String[] args) {
        int[] nums = {1,3,1,2,2,3};
        System.out.println(threeSumTarget(nums, 4));
    }

    /**
     * 从 nums[start] 开始，计算有序数组
     *
     * @param nums
     * @param start
     * @param target
     * @return
     */
    public static List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
        // 左右指针
        int lo = start, hi = nums.length - 1;
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
                List<Integer> tmp = new ArrayList<>();
                tmp.add(left);
                tmp.add(right);
                // 相等，添加到结果集中
                res.add(tmp);
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


    public static List<List<Integer>> threeSumTarget(int[] nums, int target) {
        // nums 数组必须有序
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> res = new LinkedList<>();
        // 穷举 threeSum 的第一个数
        for (int i = 0; i < n; i++) {
            // 对 target - nums[i] 计算 twoSum
            List<List<Integer>> tuples = twoSumTarget(nums, i + 1, target - nums[i]);
            // 如果存在满足条件的二元组，再加上 nums[i] 就是结果三元组
            for (List<Integer> tuple : tuples) {
                tuple.add(nums[i]);
                res.add(tuple);
            }
            // 跳过第一个数字重复的情况，否则会出现重复结果
            while (i < n - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }

}
