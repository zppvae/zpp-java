package org.java.labuladong.linkedlist;

/**
 * 链表的中间结点
 *
 * 力扣第 876 题「链表的中间结点」
 *
 * 我们让两个指针 slow 和 fast 分别指向链表头结点 head。
 *
 * 每当慢指针 slow 前进一步，快指针 fast 就前进两步，这样，当 fast 走到链表末尾时，slow 就指向了链表中点。
 *
 */
public class MiddleNode {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);

        System.out.println(middleNode(root).val);
    }

    public static ListNode middleNode(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        // 慢指针指向中点
        return slow;
    }

}
