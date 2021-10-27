package org.java.labuladong.linkedlist;

/**
 * 判断链表是否包含环
 *
 */
public class ListHasCycle {

    public static void main(String[] args) {
        ListNode l5 = new ListNode(5);
        ListNode l3 = new ListNode(3);
        ListNode l7 = new ListNode(7);
        ListNode l2 = new ListNode(2);
        ListNode l6 = new ListNode(6);
        ListNode l8 = new ListNode(8);
        ListNode l1 = new ListNode(1);

        l5.next = l3;
        l3.next = l7;
        l7.next = l2;
        l2.next = l6;
        l6.next = l8;
        l8.next = l1;
        l1.next = l2;

        System.out.println(hasCycle(l5));
    }

    /**
     * 是否有环
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇，说明含有环
            if (slow == fast) {
                return true;
            }
        }
        // 不包含环
        return false;
    }

    /**
     * 如果链表中含有环，计算这个环的起点
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // 上面的代码类似 hasCycle 函数
        if (fast == null || fast.next == null) {
            // fast 遇到空指针说明没有环
            return null;
        }

        // 重新指向头结点
        slow = head;
        // 快慢指针同步前进，相交点就是环起点
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


}
