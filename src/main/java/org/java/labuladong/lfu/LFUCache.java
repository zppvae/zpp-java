//package org.java.labuladong.lfu;
//
//import java.util.HashMap;
//import java.util.LinkedHashSet;
//
///**
// * LFU 缓存策略淘汰算法
// *
// */
//public class LFUCache {
//    /**
//     * key 到 val 的映射，我们后文称为 KV 表
//     */
//    HashMap<Integer, Integer> keyToVal;
//
//    /**
//     * key 到 freq 的映射，我们后文称为 KF 表
//     */
//    HashMap<Integer, Integer> keyToFreq;
//
//    /**
//     * freq 到 key 列表的映射，我们后文称为 FK 表
//     */
//    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
//
//    /**
//     * 记录最小的频次
//     */
//    int minFreq;
//
//    /**
//     * 记录 LFU 缓存的最大容量
//     */
//    int cap;
//
//    public LFUCache(int capacity) {
//        keyToVal = new HashMap<>();
//        keyToFreq = new HashMap<>();
//        freqToKeys = new HashMap<>();
//        this.cap = capacity;
//        this.minFreq = 0;
//    }
//
//    public int get(int key) {
//        if (!keyToVal.containsKey(key)) {
//            return -1;
//        }
//        // 增加 key 对应的 freq
//        increaseFreq(key);
//        return keyToVal.get(key);
//    }
//
//    public void put(int key, int val) {
//        if (this.cap <= 0) {
//            return;
//        }
//
//        /* 若 key 已存在，修改对应的 val 即可 */
//        if (keyToVal.containsKey(key)) {
//            keyToVal.put(key, val);
//            // key 对应的 freq 加一
//            increaseFreq(key);
//            return;
//        }
//
//        /* key 不存在，需要插入 */
//        /* 容量已满的话需要淘汰一个 freq 最小的 key */
//        if (this.cap <= keyToVal.size()) {
//            removeMinFreqKey();
//        }
//
//        /* 插入 key 和 val，对应的 freq 为 1 */
//        // 插入 KV 表
//        keyToVal.put(key, val);
//        // 插入 KF 表
//        keyToFreq.put(key, 1);
//        // 插入 FK 表
//        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
//        freqToKeys.get(1).add(key);
//        // 插入新 key 后最小的 freq 肯定是 1
//        this.minFreq = 1;
//    }
//
//    /**
//     * 删除最小频次的 key
//     */
//    private void removeMinFreqKey() {
//        // freq 最小的 key 列表
//        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
//        // 其中最先被插入的那个 key 就是该被淘汰的 key
//        int deletedKey = keyList.iterator().next();
//        /* 更新 FK 表 */
//        keyList.remove(deletedKey);
//        if (keyList.isEmpty()) {
//            freqToKeys.remove(this.minFreq);
//            // 问：这里需要更新 minFreq 的值吗？
//        }
//        /* 更新 KV 表 */
//        keyToVal.remove(deletedKey);
//        /* 更新 KF 表 */
//        keyToFreq.remove(deletedKey);
//    }
//
//
//}