package org.java.labuladong.nsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * nsum问题
 *
 *
 */
public class NSum {

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        Arrays.sort(nums);
        System.out.println(nSumTarget(nums, 4, 0, 0));
    }

    /**
     * 调用这个函数之前一定要先给 nums 排序
     *
     * @param nums
     *          数组
     * @param n
     *          几个数的和
     * @param start
     *          从下标几开始
     * @param target
     *          计算目标值
     * @return
     */
    public static List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        int sz = nums.length;
        List<List<Integer>> res = new LinkedList<>();
        // 至少是 2Sum，且数组大小不应该小于 n
        if (n < 2 || sz < n) {
            return res;
        }
        // 2Sum 是 base case
        if (n == 2) {
            // 双指针那一套操作
            int lo = start, hi = sz - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];
                if (sum < target) {
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                } else {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(left);
                    tmp.add(right);
                    res.add(tmp);
                    while (lo < hi && nums[lo] == left) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == right) {
                        hi--;
                    }
                }
            }
        } else {
            // n > 2 时，递归计算 (n-1)Sum 的结果
            for (int i = start; i < sz; i++) {
                // i + 1：从当前元素下一个元素开始计算
                List<List<Integer>> sub = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : sub) {
                    // (n-1)Sum 加上 nums[i] 就是 nSum
                    arr.add(nums[i]);
                    res.add(arr);
                }
                // 跳过第一个数字重复的情况，否则会出现重复结果
                while (i < sz - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }
}
