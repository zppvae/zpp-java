package org.java.structure.linkedList.doubleLinked;

/**
 * 双向链表节点 Description:
 * 
 * @author zpp
 * @date 2018年6月6日
 */
public class DNode<T> {

	public T data;
	public DNode<T> prev, next;// 前继指针和后继指针

	public DNode(T data, DNode<T> prev, DNode<T> next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	public DNode(T data) {
		this(data, null, null);
	}

	public DNode() {
		this(null, null, null);
	}

	public String toString() {
		return this.data.toString();
	}

}