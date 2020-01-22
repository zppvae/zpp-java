package org.java.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 约瑟夫问题
 * Description:
 * @author zpp
 * @date   2017年10月27日
 */
public class JosePhus {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入总人数：");  
		int totalNum = scanner.nextInt();  
		System.out.print("请输入报数的大小：");  
		int cycleNum = scanner.nextInt(); 
		
		System.out.println("【递归】，最后出列的人的初始位置(下标)；"+fun2(totalNum,cycleNum));
//		fun3(totalNum,cycleNum);
	}
	
	/**
	 * 效率高
	 * 使用公式的方式解决
	 * f(1)=0
	 * f(n)=(f(n-1)+m)%n
	 */
	public static void fun(int totalNum,int cycleNum){
		int s = 0;
		for (int i=1; i<=totalNum; i++)  {
	        s=(s+cycleNum)%i;
		}
		System.out.println("最后出列的人的初始位置(下标)；"+s);
	}
	
	/**
	 * 使用递归的方式解决
	 * f(1)=0
	 * f(n)=(f(n-1)+m)%n
	 */
	public static int fun2(int totalNum,int cycleNum){
		if(totalNum == 1){
			return 0;
		}else{
			return (fun2(totalNum - 1,cycleNum) + cycleNum) % totalNum;
		}
	}
	
	/**
	 * 
	 * @param totalNum
	 * @param cycleNum
	 * @return
	 */
	public static void fun3(int totalNum,int cycleNum){
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 1;i <= totalNum;i++){
			list.add(i);
		}
		int k = 0;
		while(list.size() > 1){
			k = k + cycleNum;
			k = k % list.size() - 1;
			
//			System.out.println("索引位置："+k);
			
			if(k < 0){
//				System.out.println("被移除的元素："+list.get(list.size() - 1));
				list.remove(list.size() - 1);
				k = 0;
			}else{
//				System.out.println("被移除的元素："+list.get(k));
				list.remove(k);
			}
			
		}
		
		System.out.println("最后剩余的元素："+list.get(0));
	}
	
}
