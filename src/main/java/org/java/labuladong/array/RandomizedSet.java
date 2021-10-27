package org.java.labuladong.array;

import java.util.*;

/**
 *
 * O(1) 时间，查找/删除数组中的任意元素
 *
 */
public class RandomizedSet {
    // 存储元素的值
    public List<Integer> nums;
    // 记录每个元素对应在 nums 中的索引
    public Map<Integer, Integer> valToIndex;

    public Random random;

    public RandomizedSet() {
        valToIndex = new HashMap<>();
        nums = new ArrayList<>();
        random = new Random();
    }

    public boolean insert(int val) {
        // 若 val 已存在，不用再插入
        if (valToIndex.containsKey(val)) {
            return false;
        }
        // 若 val 不存在，插入到 nums 尾部，
        // 并记录 val 对应的索引值
        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    boolean remove(int val) {
        // 若 val 不存在，不用再删除
        if (!valToIndex.containsKey(val)) {
            return false;
        }
        // 先拿到 val 的索引
        int index = valToIndex.get(val);
        // 将最后一个元素对应的索引修改为 index
        valToIndex.put(nums.size() - 1, index);
        // 交换 val 和最后一个元素
        nums.set(index, nums.size() - 1);
        // 在数组中删除元素 val
        nums.remove(nums.size() - 1);
        // 删除元素 val 对应的索引
        valToIndex.remove(val);
        return true;
    }

    int getRandom() {
        // 随机获取 nums 中的一个元素
        return nums.get(random.nextInt(nums.size() - 1));
    }

    public static void main(String[] args) throws Exception {
        RandomizedSet rSet = new RandomizedSet();
//        System.out.println(rSet.getRandom());
        System.out.println(rSet.insert(1));
        System.out.println(rSet.insert(2));
        System.out.println(rSet.insert(2));
        System.out.println(rSet.insert(3));
        System.out.println(rSet.remove(2));
        System.out.println(rSet.insert(2));
        System.out.println(rSet.getRandom());
        System.out.println(rSet.insert(234));
        System.out.println(rSet.insert(23));
        System.out.println(rSet.insert(22));
        System.out.println(rSet.getRandom());
        System.out.println(rSet.remove(245));
        System.out.println(rSet.remove(234));
        System.out.println(rSet.getRandom());
    }
}