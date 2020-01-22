package org.java.basic;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 遇见父类类型使用泛型限定进行升华
 * @author zpp
 *
 */
public class GenericFuncitonXV{  
	
	/**
	 * 使用父类固定的普通方法
	 * @param al
	 */
    public void printColl(ArrayList<? extends Number> al){  
        Iterator<?extends Number> it =al.iterator();  
        while(it.hasNext()){  
            Object obj =it.next();  
            System.out.print(obj+" ");  
        }  
        System.out.println();  
    }  
    
    public static <T extends Integer> int add(T ...inteArr){  
        int sum =0;  
        for(T x: inteArr)  
            sum+=x;  
                return sum;  
    } 
    
    public static <T> void print(T ...arr){  
        for(T x: arr){
        	System.out.println(x);
        }
    } 
    
    public static void main(String[] args) {  
         
        GenericFuncitonXV genObj=new GenericFuncitonXV();  
        ArrayList<Integer>al1 =new ArrayList<Integer>();  
        al1.add(123);  
        al1.add(456);  
        al1.add(789);  
        genObj.printColl(al1);  
        System.out.println("**********************");  
        ArrayList<Long>al2 =new ArrayList<Long>();  
        al2.add(12345678l);  
        al2.add(45645678l);  
        al2.add(78945678l);  
        genObj.printColl(al2);  
    }  
}  