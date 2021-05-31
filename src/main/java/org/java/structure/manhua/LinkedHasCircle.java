package org.java.structure.manhua;

/**
 * 链表是否有环
 */
public class LinkedHasCircle {

    public static class Node {
        private int data;
        /**
         * 下一个节点
         */
        private Node next;

        public Node (int data) {
            this.data = data;
        }
    }

    public static boolean hasCircle(Node head){
        Node p1 = head;
        Node p2 = head;

        while (p2 != null && p2.next != null) {
            // p1每次走一步
            p1 = p1.next;
            // p2每次走两步
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Node n1 = new Node(5);
        Node n2 = new Node(8);
        Node n3 = new Node(7);
        Node n4 = new Node(2);
        Node n5 = new Node(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n2;

        System.out.println(hasCircle(n1));
    }
}
