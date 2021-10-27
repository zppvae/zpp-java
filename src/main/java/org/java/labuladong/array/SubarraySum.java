package org.java.labuladong.array;

/**
 * 子数组求和
 *
 * 前缀和技巧：解决子数组问题
 *
 * 算出一共有几个和为 k 的子数组
 *
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484488&idx=1&sn=848f76e86fce722e70e265d0c6f84dc3&chksm=9bd7fa40aca07356a6f16db72f5a56529044b1bdb2dcce2de4efe59e0338f0c313de682aef29&scene=21#wechat_redirect
 *
 * //TODO 优化算法暂时没有看懂
 */
public class SubarraySum {

    public static void main(String[] args) {

    }

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // 构造前缀和
        int[] sum = new int[n + 1];
        sum[0] = 0;
        // 前缀和技巧
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        int ans = 0;
        // 穷举所有子数组
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // sum of nums[j..i-1]
                if (sum[i] - sum[j] == k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
