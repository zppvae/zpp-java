package org.java.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 * 泛型
 * https://blog.csdn.net/benjaminzhang666/article/details/9497267
 * 
 * (1). 向下限定
	[1]. 格式：<? extends T>：T限定，T的子类扩展
	表示?取值的类型只能是T本身或者其子类。
	[2]. 举例：
	【正确】Vector<? extends Number> x =newVector<Integer>();
	由于Integer是Number的子类，所以编译通过。
	【错误】Vector<? extends Number> x = new Vector<Strings>();
	由于String不是Number的子类，所以编译不能通过。
  (2). 向上限定
	[2]. 格式：<? super T>：T限定，T的父类扩展
	表示?取值的类型只能是T本身或者其父类。
	[2]. 举例：
	【正确】Vector<? super Integer> x=new Vector<Number>();
	由于Number是Integer的父类，所以编译通过。
	【错误】Vector<? super Integer> x = new Vector<Byte>();
	由于Byte不是Integer的父类，所以编译不能通过。
 * @author zpp
 *
 */
public class GenericTest {
	
	public static void main(String[] args) {
		Vector v1 =new Vector<String>();

		Vector<Object> v2 =v1;
		
//		GenericDemo d = new GenericDemo();  
//        d.show(new Integer(50));  
//        d.show("ABCDE");  
//        d.print("FGHJI"); 
		
		ArrayList<String>alS =new ArrayList<String>();  
        alS.add("ABC");  
        printColl(alS);  
         
        ArrayList<Integer> alI =new ArrayList<Integer>();  
        alI.add(123);  
        printColl(alI);  
        
        Collection<String>[]collArrGeneric =new ArrayList[2];  //new ArrayList<Strings>[2]  错误
        
        collArrGeneric[0]=new ArrayList<String>();  
        collArrGeneric[0].add("ABC1");  
	}
	
	/**
	 * 占位 ?
	 * @param al
	 */
	public static void printColl(ArrayList<?> al){  
        for(int i=0; i<al.size(); i++){  
            System.out.print(al.get(i)+ " ");  
        }  
    } 
	
	public List<? super Object> test(){ return null;}
	
}
