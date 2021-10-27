package org.java.labuladong.lru;

/**
 * 双向链表
 *
 */
public class DoubleList {
    // 头尾虚节点
    private LRUNode head, tail;

    // 链表元素数
    private int size;

    public DoubleList() {
        // 初始化双向链表的数据
        head = new LRUNode(0, 0);
        tail = new LRUNode(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * 在链表尾部添加节点 x，时间 O(1)
     *
     * @param x
     */
    public void addLast(LRUNode x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    /**
     * 删除链表中的 x 节点（x 一定存在）
     * 由于是双链表且给的是目标 Node 节点，时间 O(1)
     *
     * @param x
     */
    public void remove(LRUNode x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    /**
     * 删除链表中第一个节点，并返回该节点，时间 O(1)
     *
     * @return
     */
    public LRUNode removeFirst() {
        if (head.next == tail) {
            return null;
        }
        LRUNode first = head.next;
        remove(first);
        return first;
    }

    /**
     * 返回链表长度，时间 O(1)
     * @return
     */
    public int size() { return size; }

}