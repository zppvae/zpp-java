package org.java.labuladong.linkedlist;

/**
 * 两个链表是否相交
 *
 * 力扣第 160 题「相交链表」
 *
 * 让 p1 遍历完链表 A 之后开始遍历链表 B，让 p2 遍历完链表 B 之后开始遍历链表 A，这样相当于「逻辑上」两条链表接在了一起。
 */
public class IntersectionNode {

    public static void main(String[] args) {
        ListNode l5 = new ListNode(5);
        ListNode l3 = new ListNode(3);
        ListNode l7 = new ListNode(7);
        ListNode l2 = new ListNode(2);
        l5.next = l3;
        l3.next = l7;
        l7.next = l2;

        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n2 = new ListNode(2);
        n6.next = n7;
        n7.next = n2;

        System.out.println(getIntersectionNode(l5, n6).val);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // p1 指向 A 链表头结点，p2 指向 B 链表头结点
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            if (p1 != null && p2 != null && p1.val == p2.val) {
                return p1;
            }
            // p1 走一步，如果走到 A 链表末尾，转到 B 链表
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            // p2 走一步，如果走到 B 链表末尾，转到 A 链表
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }

}
