package org.java.labuladong.lru;

import java.util.HashMap;

/**
 *
 * LRU 缓存算法
 *
 * 哈希表查找快，但是数据无固定顺序；链表有顺序之分，插入删除快，但是查找慢。
 *
 * LRU 缓存算法的核心数据结构就是哈希链表，双向链表和哈希表的结合体，
 * 结合一下，形成一种新的数据结构：哈希链表 {@link java.util.LinkedHashMap}。
 *
 * 1、为什么必须要用双向链表?
 *  因为我们需要删除操作。删除一个节点不光要得到该节点本身的指针，
 *  也需要操作其前驱节点的指针，而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)
 *
 * 注意: 此算法实现的双链表 API 只能从尾部插入，也就是说靠尾部的数据是最近使用的，靠头部的数据是最久为使用的。
 *
 */
public class LRUCache {
    /**
     * key -> Node(key, val)
     */
    private HashMap<Integer, LRUNode> map;

    /**
     * Node(k1, v1) <-> Node(k2, v2)...
     */
    private DoubleList cache;

    /**
     * 最大容量
     */
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    /**
     * 将某个 key 提升为最近使用的
     *
     * @param key
     */
    private void makeRecently(int key) {
        LRUNode x = map.get(key);
        // 先从链表中删除这个节点
        cache.remove(x);
        // 重新插到队尾
        cache.addLast(x);
    }

    /**
     * 添加最近使用的元素
     *
     * @param key
     * @param val
     */
    private void addRecently(int key, int val) {
        LRUNode x = new LRUNode(key, val);
        // 链表尾部就是最近使用的元素
        cache.addLast(x);
        // 别忘了在 map 中添加 key 的映射
        map.put(key, x);
    }

    /**
     * 删除某一个 key
     *
     * @param key
     */
    private void deleteKey(int key) {
        LRUNode x = map.get(key);
        // 从链表中删除
        cache.remove(x);
        // 从 map 中删除
        map.remove(key);
    }

    /**
     * 删除最久未使用的元素
     *
     */
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        LRUNode deletedNode = cache.removeFirst();
        // 同时别忘了从 map 中删除它的 key
        int deletedKey = deletedNode.key;
        map.remove(deletedKey);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 将该数据提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key, val);
            return;
        }

        if (cap == cache.size()) {
            // 删除最久未使用的元素
            removeLeastRecently();
        }
        // 添加为最近使用的元素
        addRecently(key, val);
    }
}