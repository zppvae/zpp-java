package org.java.leetcode.linkedList;

/**
 * 反转链表
 * @author zpp
 * @date 2019/4/30 15:59
 */
public class ReverseLinkedList {

    public static void main(String[] args){

    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //当前结点
        ListNode curr = head;
        //前一个节点
        ListNode pre = null;
        while (curr != null) {
            ListNode next = curr.next;
            //反转当前节点的下个节点的方向
            curr.next = pre;
            //设置下次循环的前一个节点
            pre = curr;
            //设置下次循环时的节点
            curr = next;
        }
        return pre;
    }

    static class ListNode{
        private int val;

        private ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }
}
