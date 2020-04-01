package org.java.leetcode;

/**
 * 双向链表 + HashMap
 *
 * HashMap#get(key) 时间复杂度为O(1)
 *
 * 1、使用数组可以实现，但是无法实现 O(1)
 * 2、使用单向链表可以实现，但无法解决get()后，无法达到 O(1)
 * 3、想要 get() 达到 O(1)，必须加上哈希表
 * 4、想要使get(key)时，断了的链表链接起来并且时间复杂度为 O(1)，需使用双向链表
 * 5、最终算法：HashMap + Double Linked List
 *
 * JDK 中的 LRU：{@link java.util.LinkedHashMap}
 *
 * 扩展：
 * 1、哨兵写法（dummy head，dummy tail）
 * 2、线程安全（加锁）
 * 3、高并发（CAS操作）
 * 4、考虑 ConcurrentHashMap
 * 5、如果容忍一定的精确性损失（读写锁）
 * 6、分布式LRU
 */
public class LRUCache {

    public LRUCache(int capacity) {

    }

    public int get(int key) {
        return key;
    }

    public void put(int key, int value) {

    }
}
