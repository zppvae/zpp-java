package org.java.labuladong.array;

/**
 * 差分数组构造类
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487011&idx=1&sn=5e2b00c1c736fd7afbf3ed35edc4aeec&chksm=9bd7f02baca0793d569a9633cc14117e708ccc9eb41b7f0add430ea78f22e4f2443f421c6841&scene=21#wechat_redirect
 *
 *
 */
public class Difference {
    // 差分数组
    private int[] diff;

    public Difference(int[] nums) {
        assert nums.length > 0;
        diff = new int[nums.length];
        // 构造差分数组
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    /* 给闭区间 [i,j] 增加 val（可以是负数）*/

    /**
     * 当j+1 >= diff.length时，说明是对nums[i]及以后的整个数组都进行修改，那么就不需要再给diff数组减val了
     * 比如：nums 长度是10，给[5, 9]之间的数据加 3，相当于给5之后的整个数组加3，就没有必要再对数组减val
     * @param i
     * @param j
     * @param val
     */
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    public int[] result() {
        int[] res = new int[diff.length];
        // 根据差分数组构造结果数组
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }
}