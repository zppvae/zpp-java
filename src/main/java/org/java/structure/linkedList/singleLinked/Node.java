package org.java.structure.linkedList.singleLinked;

/**
 * 单向链表节点
 * Description:
 * @author zpp
 * @date   2018年6月5日
 */
public class Node<T> {
	/** 数据域*/
    public T data;
    public Node<T> next;//地址域

    public Node(){

    }

    public Node(T data){
        this.data=data;
    }

    public Node(T data,Node<T> next){
        this.data=data;
        this.next=next;
    }

}