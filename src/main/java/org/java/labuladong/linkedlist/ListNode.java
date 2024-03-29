package org.java.labuladong.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode (int val) {
        this.val = val;
    }
}
