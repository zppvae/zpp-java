package org.java.labuladong.linkedlist;

import com.alibaba.fastjson.JSON;

/**
 * 删除链表的倒数第 k 个节点
 *
 * 力扣第 19 题「删除链表的倒数第 N 个结点」
 *
 */
public class RemoveKNodeFromEnd {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);

        System.out.println(JSON.toJSONString(removeNthFromEnd(root, 2)));
    }

    /**
     * 快慢指针
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast, slow;
        fast = slow = head;
        // 快指针先前进 n 步
        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            // 如果此时快指针走到头了，
            // 说明倒数第 n 个节点就是第一个节点
            return head.next;
        }
        // 让慢指针和快指针同步向前
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // slow.next 就是倒数第 n 个节点，删除它
        slow.next = slow.next.next;
        return head;
    }

//    public static ListNode removeNthFromEnd(ListNode head, int n) {
//        // 虚拟头结点
//        ListNode dummy = new ListNode(-1);
//        dummy.next = head;
//        // 删除倒数第 n 个，要先找倒数第 n + 1 个节点
//        ListNode x = findFromEnd(dummy, n + 1);
//        // 删掉倒数第 n 个节点
//        x.next = x.next.next;
//        return dummy.next;
//    }
//
//
//    /**
//     * 返回链表的倒数第 k 个节点
//     *
//     * 链表长度 n：
//     * 1、p1先走 k 步，p1再走 n - k 步到达链表最后
//     * 2、p1、p2同时走 n-k 步，p2此时指向第 n - k 个节点，即倒数第 k 个节点
//     *
//     * @param head
//     * @param k
//     * @return
//     */
//    public static ListNode findFromEnd(ListNode head, int k) {
//        ListNode p1 = head;
//        // p1 先走 k 步
//        for (int i = 0; i < k; i++) {
//            p1 = p1.next;
//        }
//        ListNode p2 = head;
//        // p1 和 p2 同时走 n - k 步
//        while (p1 != null) {
//            p2 = p2.next;
//            p1 = p1.next;
//        }
//        // p2 现在指向第 n - k 个节点
//        return p2;
//    }

}
