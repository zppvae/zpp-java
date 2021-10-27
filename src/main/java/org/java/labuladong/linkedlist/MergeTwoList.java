package org.java.labuladong.linkedlist;

import com.alibaba.fastjson.JSON;

/**
 * 合并两个有序链表
 *
 *
 * 代码中还用到一个链表的算法题中是很常见的「虚拟头结点」技巧，
 * 也就是 dummy 节点。你可以试试，如果不使用 dummy 虚拟节点，
 * 代码会复杂很多，而有了 dummy 节点这个占位符，可以避免处理空指针的情况，降低代码的复杂性。
 *
 * 力扣第 21 题「合并两个有序链表」
 *
 * @author zhupp
 */
public class MergeTwoList {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode result = mergeTwoLists(l1, l2);
        System.out.println(JSON.toJSONString(result));
    }

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        ListNode p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            // p 指针不断前进
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return dummy.next;
    }

}
