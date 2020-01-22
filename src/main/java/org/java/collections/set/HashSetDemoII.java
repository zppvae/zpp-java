package org.java.collections.set;

import java.util.HashSet;
import java.util.Iterator;

/**
 * set无序不可重复
 * @author zpp
 *
 */
public class HashSetDemoII {  
    public static void sop(Object o){  
        System.out.println(o);  
    }  
   
    public static void main(String[] args){  
    	testRepeatObj();

    }  
    
    /**
     * 测试重复的字符串
     * String类的equals方法重写过
     */
    public void testRepeatStr(){
    	HashSet hs = new HashSet();  
        sop("java001 really add? "+ hs.add("java001"));  
        sop("java001 really add? "+ hs.add("java001"));  
        sop("java002 really add? "+ hs.add("java002"));  
        sop("java003 really add? "+ hs.add("java003"));  
        sop("java003 really add? "+ hs.add("java003"));  
         
        sop("最终元素集合中的内容...");  
        Iterator it =hs.iterator();  
        while(it.hasNext()){  
            sop(it.next());  
        }  
    }
    
    /**
     * 测试重复对象
     * HashSet在存储元素的时候，是先查看两个对象的哈希值是否一样的，相等才查看equals
     */
    public static void testRepeatObj(){
    	 HashSet hs =new HashSet();  
         hs.add(new Person("a1", 11));  
         hs.add(new Person("a2", 12));  
         hs.add(new Person("a3", 13));  
         hs.add(new Person("a2", 12));  
    
         Iterator it =hs.iterator();  
         while(it.hasNext()){  
             sop(it.next());  
         }  
    }
    
    
    
    
}  