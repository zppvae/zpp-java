package org.java.labuladong.huishuo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * 全排列
 *
 * 回溯算法就是纯暴⼒穷 举，复杂度⼀般都很⾼。
 *
 */
public class FullArray {

    public static void main(String[] args) {
        List<List<Integer>> res = new LinkedList<>();
        int[] nums = {1,2,3};

        List<List<Integer>> result = permute(nums, res);
        System.out.println(result);
    }

    /**
     * 主函数，输⼊⼀组不重复的数字，返回它们的全排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums, List<List<Integer>> res) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track, res);
        return res;
    }

    /**
     * 路径：记录在 track 中
     * 选择列表：nums 中不存在于 track 的那些元素,
     * 结束条件：nums 中的元素全都在 track 中出现
     *
     * @param nums
     * @param track
     */
    public static void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i])) {
                continue;
            }
            // 做选择
            track.add(nums[i]);
            // 进⼊下⼀层决策树
            backtrack(nums, track, res);
            // 取消选择
            track.removeLast();
        }
    }
}
