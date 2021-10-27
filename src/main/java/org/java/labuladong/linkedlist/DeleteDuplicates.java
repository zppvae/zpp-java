package org.java.labuladong.linkedlist;

/**
 * 删除重复元素，与数组相同 {@link org.java.labuladong.array.RemoveDuplicates}
 * 唯一的区别是把数组赋值操作变成操作指针而已
 *
 * 力扣83、删除链表中的重复项
 *
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247487466&idx=1&sn=e0c21cf8c3a76cfc4844b1269b658344&chksm=9bd7f1e2aca078f49d1a4090f80969bd4dc415fae6756e488e8b710965ea12baa4d3ac9b0d46&scene=21#wechat_redirect
 *
 */
public class DeleteDuplicates {

    public static void main(String[] args) {

    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                // nums[slow] = nums[fast];
                slow.next = fast;
                // slow++;
                slow = slow.next;
            }
            // fast++
            fast = fast.next;
        }
        // 断开与后面重复元素的连接
        slow.next = null;
        return head;
    }
}
