package org.java.leetcode.linkedList;

import lombok.extern.slf4j.Slf4j;


/**
 * 旋转链表
 *
 * https://www.cnblogs.com/xugenpeng/p/9914281.html
 * @author zpp
 * @date 2019/4/30 14:14
 */
@Slf4j
public class RotateLinkedList {

    public static void main(String[] args){
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;

        ListNode newHead = rotateRight(n1,2);
        log.info("新的头结点:{}",newHead.val);
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }
        //链表长度
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            ++ length;
            tail = tail.next;
        }
        k = k % length;

        if (k == 0) {
            return head;
        }
        //第k + 1个元素
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < k; i++) {
            p2 = p2.next;
        }

        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        ListNode newHead = p1.next;
        p1.next = null;
        p2.next = head;

        return newHead;
    }

    static class ListNode{
        private int val;

        private ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }
}
