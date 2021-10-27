package org.java.labuladong.binarySearch;

/**
 * 二分查找
 *
 */
public class FindNumber {

    public static void main(String[] args) {
        int[] nums = {1,3,6,7,8};
        System.out.println(binarySearch(nums, 7));

        int[] nums2 = {1,3,6,6,6,6,6,7,8};
        System.out.println(left_bound(nums2, 6));
        System.out.println(right_bound(nums2, 6));

    }

    /**
     * 最基本的二分查找算法
     *
     * @param nums
     *          元素不可重复
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // [mid+1, right]
                left = mid + 1;
            } else if (nums[mid] > target) {
                // [left,mid-1]
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 寻找左侧边界的二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 最后要检查 left 越界的情况
        if (left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }

    /**
     * 寻找右侧边界的二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int right_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        // 最后要检查 right 越界的情况
        if (right < 0 || nums[right] != target)
            return -1;
        return right;
    }

}
