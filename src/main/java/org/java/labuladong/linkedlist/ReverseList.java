package org.java.labuladong.linkedlist;

/**
 *
 * 反转链表
 *
 */
public class ReverseList {

    // 后驱节点
    static ListNode successor = null;

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(reverseBetween(head, 2, 4).getVal());
    }

    /**
     *  反转以 head 为起点的 n 个节点，返回新的头结点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    /**
     * 反转链表的一部分 [m, n] 区间的元素，m=1时，与reverseN 效果一样
     *
     * 如果我们把「head」的索引视为 1，那么我们是想从第m个元素开始反转对吧；
     * 如果把「head.next」的索引视为 1 呢？那么相对于「head.next」，
     * 反转的区间应该是从第m - 1个元素开始的；那么对于「head.next.next」呢 ？
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
}
