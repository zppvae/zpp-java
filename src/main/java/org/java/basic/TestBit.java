package org.java.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 位移法则
 * 法则一：任何数左移（右移）32的倍数位等于该数本身。
 * 法则二：在位移运算m<<n的计算中，若n为正数，则实际移动的位数为n%32，若n为负数，则实际移动的位数为(32+n%32)，右移，同理。
 * 左移是乘以2的幂，对应着右移则是除以2的幂。
 * 
 * 异或：0^0=0,1^1=0,0^1=1
 * 
 * 其实java中的异或运算法则完全遵守数学中的计算法则：
	①a ^ a =0
	②a ^ b =b ^ a
	③a ^b ^ c = a ^ (b ^ c) = (a ^ b) ^ c;
	④d = a ^b ^ c 可以推出 a = d ^ b ^ c.
	⑤a ^ b ^a = b.
 * 
 * Description:
 * @author zpp
 * @date   2018年3月20日
 */
public class TestBit {
	
	public static void main(String[] args) {
//		System.out.println(3 << 3);// 3左移3位，3*2的次方
//		
//		System.out.println(3 & ~4);
//		
//		//测试第 k位     s & (1 << k)
//		System.out.println(2 & (1 << 3));
//		
//		System.out.println(-6 >> 1);
//		
//		//无符号右移（>>>）低位溢出，高位补0
//		System.out.println("Integer.MAX_VALUE="+Integer.toBinaryString(-1>>>1));
//		
//		//判断一个数的奇偶性  偶数的最低位是0，1的最低位1
//		System.out.println((10&1) == 1 ? "奇数" : "偶数");
//		
//		
//		bitExchange(2,8);

	}
	
	/**
	 * 异或运算实现两个数的交换
	 * @param a
	 * @param b
	 */
	public static void bitExchange(int a,int b) {
		System.out.println("交换前,a="+a+",b="+b);
		a = a^b;
		b = b^a;//b=b^(a^b)         ->  b=a
		a = a^b;//a=(a^b)^(b^(a^b)) ->  a=b
		System.out.println("交换后,a="+a+",b="+b);
	}
	
	
	public int add(int a,int b) {
        int res=a;
        int xor=a^b;//得到原位和
        int forward=(a&b)<<1;//得到进位和
        if(forward!=0){//若进位和不为0，则递归求原位和+进位和
            res=add(xor, forward);
        }else{
            res=xor;//若进位和为0，则此时原位和为所求和
        }
        return res;                
    }
}
