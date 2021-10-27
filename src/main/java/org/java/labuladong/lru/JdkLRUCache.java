package org.java.labuladong.lru;

import java.util.LinkedHashMap;

/**
 * 使用jdk 中的数据结构实现LRU 算法
 *
 * 设计思路：
 *  1. 参数：容量参数、LinkedHashMap 参数
 *  2. 提供 get()、put()、makeRecently()方法
 *  3. get()思路：
 *     a. key不存在返回-1
 *     b. 将 key 变为最近使用
 *     c. 返回val
 *  4. put()思路：
 *     a. key不存在：判断容量，容量超过，删除头部元素，再put key
 *     b. key存在：put新值，然后将 key 变为最近使用
 *  5. makeRecently()思路：
 *     a. 获取值，删除，再次put到尾部
 */
public class JdkLRUCache {

    /**
     * 容量
     */
    int cap;

    /**
     * 缓存
     */
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public JdkLRUCache(int capacity) {
        this.cap = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            // 修改 key 的值
            cache.put(key, val);
            // 将 key 变为最近使用
            makeRecently(key);
            return;
        }

        if (cache.size() >= this.cap) {
            // 链表头部就是最久未使用的 key
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的 key 添加链表尾部
        cache.put(key, val);
    }

    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }
}
