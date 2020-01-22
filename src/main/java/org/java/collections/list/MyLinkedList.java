package org.java.collections.list;

/**
 * 实现单向链表
 * Description:
 * @author zpp
 * @date   2017年10月27日
 */
public class MyLinkedList<E> {
	
	/** 头结点*/
	private Node<E> head = null;
	
	/** 数组大小*/
	private int size = 0;
	
	
	public MyLinkedList(){
		this.head = new Node<E>();
	}
	
	
	public boolean add(E e) {
		if(size == 0){
			head.item = e;
		}else{
			Node<E> item = new Node<E>();
			item.item = e;
			//最后一个节点
			Node<E> last = getNode(size-1);
			last.next = item;
		}
		size++;
		return true;
	}
	
	public boolean remove(E e){
		Node<E> cur = head;
		while(e != cur.item){
			cur = cur.next;
			if(cur.item == e){
				break;
			}
		}
		Node<E> nextNode = cur.next;
		cur.item = nextNode.item;
		cur.next = nextNode.next;
		nextNode.next = null;
		size --;
		return true;
	}
	
	/**
	 * 获取指定位置的节点
	 * @param index
	 * @return
	 */
	private Node<E> getNode(int index){
		Node<E> item = new Node<E>();
		item = head;
		
		int cur = 0;
		while(cur != index){
			item = item.next;
			cur ++;
		}
		
		return item;
	}
	
	public E get(int index){
		return getNode(index).item;
	}
	
	public int size() {
        return size;
    }
	
	
	private static class Node<E> {
		/** 当前元素*/
        E item;
        
        /** 下一节点*/
        Node<E> next;
        
        Node(){}
        
        Node(E element) {
            this.item = element;
        }
        
        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
	
	
}
