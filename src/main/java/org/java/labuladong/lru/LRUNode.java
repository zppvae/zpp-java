package org.java.labuladong.lru;

import lombok.Data;

/**
 *
 * LRU 节点
 */
@Data
public class LRUNode {

    public int key;

    public int val;

    /**
     * 下一个节点
     */
    public LRUNode next;

    /**
     * 前一个节点
     */
    public LRUNode prev;

    public LRUNode (int key, int val) {
        this.key = key;
        this.val = val;
    }
}
