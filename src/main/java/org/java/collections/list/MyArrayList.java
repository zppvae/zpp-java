package org.java.collections.list;

import java.util.Objects;

/**
 * 实现线性表顺序存储结构
 * Description:
 * @author zpp
 * @date   2017年10月26日
 */
public class MyArrayList<E> {
	
	transient Object[] elemData = null;
	
	/** 空数组*/
	private static final Object[] EMPTY_ELEMENTDATA = {};
	
	/** 当前数组的下标*/
	private int current;
	
	/** 当前数组的大小*/
	private int size;
	
	/** 默认初始化容量*/
	private static final int DEFAULT_CAPACITY = 10;
	
	
	public MyArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	
	public MyArrayList(int initSize) {
		if (initSize > 0) {
            this.elemData = new Object[initSize];
        } else if (initSize == 0) {
            this.elemData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
            		initSize);
        }
	}
	
	/**
	 * 验证下表合法性
	 * @param index
	 */
	private void checkIndex(int index) {
		if (index < 0 || index >= current) {
			throw new IndexOutOfBoundsException("数组index错误：" + index); 
		}
	}
	
	/**
	 * 
	 * @param current 当前容量
	 */
	private void ensureCapacity(int cur){
		if (cur == this.size) {
			this.size = this.size + 10;
			Object[] newData = new Object[size];
			for (int i = 0;i < size;i++) {
				newData[i] = elemData[i];
			}
			elemData = newData;
		}
	}
	
	
	public boolean add(E e) {
		ensureCapacity(current);
		elemData[current] = e;
		//添加完元素后，指针向后移动一位
		current ++;
		return true;
	}
	
	/**
	 * 按照数组下标移除元素
	 * @param index
	 * @return
	 */
	public E remove(int index) {
		checkIndex(index);
		
		Object[] newEleData = new Object[size];
		
		E oldValue = elementData(index);
		
		for (int i = 0;i < current;i++) {
			if (i >= index){
				newEleData[i] = elemData[i + 1];
			}else{
				newEleData[i] = elemData[i];
			}
		}
		elemData = newEleData;
		elemData[current] = null;
		current --;
		return oldValue;
	}
	
	/**
	 * 按照元素从数组中移除元素
	 * @param o
	 * @return
	 */
	public boolean remove(Object o) {
		if (o == null) {
			for (int i = 0;i < current;i++) {
				if (elemData[i] == null) {
					fastRemove(i);
					return true;
				}
			}
		}else{
			for (int i = 0;i < current;i++) {
				if (Objects.equals(o, elemData[i])) {
					fastRemove(i);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 根据索引快速移除元素
	 * @param index
	 */
	public void fastRemove(int index) {
		int numMoved = current - index - 1;
        if (numMoved > 0)
            System.arraycopy(elemData, index+1, elemData, index,
                             numMoved);
        elemData[--current] = null;
	}
	
	
	E elementData(int index) {
        return (E) elemData[index];
    }
	
	public E get(int index) {
		checkIndex(index);
		return elementData(index);
	}
	 
	public int size() {
		return this.current;
	}
	
	
	
	
	
}
