package org.java.leetcode;

import java.util.Stack;

/**
 * 中缀表达式转后缀表达式
 * 
 * Description:
 * @author zpp
 * @date   2018年6月12日
 */
public class CalculateExpression {
	
	private static final Character SPLIT = ' ';
	/**
	 * 转后缀表达式
	 * 如果遇到任何其他的操作符，如（“+”， “*”，“（”）等，从栈中弹出元素存入,直到遇到发现更低优先级的元素(或者栈为空)为止。弹出完这些元素后，才将遇到的操作符压入到栈中
	 * 方法：数字输出，运算符进栈，括号匹配出栈，栈顶优先级低出栈
	 * 中缀表达式 9+（3-1）*3+10/2 = 20 转后缀931-3*+10 2/+
	 * 
	 *  写程序将中缀表达式转出后缀表达式，再计算其结果
		中缀表达式 9+（3-1）*3+10/2 = 20 转后缀931-3*+10 2/+
		931-3*+10 2/+
		923*+10 2/ +
		96+10 2/ +
		15 10 2/ +
		15 5 +
		20
	 * @param expression
	 * @return
	 */
	public static String toPostfix(String expression) {
		Stack<String> stack = new Stack<>();
		
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < expression.length()) {
			char c = expression.charAt(i);
			
			switch (c) {
			case '+':
				while (!stack.isEmpty()) {
					sb.append(stack.pop()+SPLIT);
				}
				stack.push(c+"");
				i++;
				break;
			case '-':
				while (!stack.isEmpty() && stack.peek().equals("+") && !"(".equals(stack.peek())) {
					sb.append(stack.pop()+SPLIT);
				}
				stack.push(c+"");
				i++;
				break;
			case '*':
				while (!stack.isEmpty() && !"+".equals(stack.peek()) && !"-".equals(stack.peek()) && !"(".equals(stack.peek())) {
					sb.append(stack.pop()+SPLIT);
				}
				stack.push(c+"");
				i++;
				break;
			case '/':
				while (!stack.isEmpty() && !"+".equals(stack.peek()) && !"-".equals(stack.peek()) && !"(".equals(stack.peek())) {
					sb.append(stack.pop()+SPLIT);
				}
				stack.push(c+"");
				i++;
				break;
			case '(':
				stack.push(c+"");
				i++;
				break;
			case ')':
				String out = stack.pop();
				while (out != null && !out.equals("(")) {
					sb.append(out+SPLIT);
					out = stack.pop();
				}
				i++;
				break;
			default:
				while (c >= '0' && c <= '9') {
					sb.append(c);
					i++;
					if (i < expression.length()) {
						c = expression.charAt(i);
					} else {
						c = '=';
					}
				}
				sb.append(SPLIT);
			}
		}
		
		while (!stack.isEmpty()) {
			sb.append(stack.pop()+SPLIT);
		}
		return sb.toString();
	}
	
	/**
	 * 求后缀表达式的值
	 * 1)如果c是数字，先将其转换为整数再入栈 
	 * 2)如果是运算符，将两个操作数出栈，计算结果再入栈 
	 * 3)重复1）和2）直到后缀表达式结束，最终栈内的元素即为计算的结果。 
	 *  931-3*+10 2/+
		923*+10 2/ +
		96+10 2/ +
		15 10 2/ +
		15 5 +
		20
	 * @param expression
	 * @return
	 */
	public static Integer getPostfixVal(String expression){
		Stack<Integer> stack = new Stack<>();
		int index = 0,result;
		while (index < expression.length()) {
			char c = expression.charAt(index);
			if (c >= '0' && c <= '9') {
				result = 0;
				while (c != ' ') {
					// 将整数字符转为整数值ch=90
					result = result * 10 + Integer.parseInt(c + "");
					index++;
					c = expression.charAt(index);
				}
				index++;
				stack.push(result);
			} else {
				if (c == ' ') {
					index++;
					continue;
				}
				Integer x = stack.pop();
				Integer y = stack.pop();
				switch (c) {
					case '+':
						stack.push(y + x);
						break;
					case '-':
						stack.push(y - x);			
						break;
					case '*':
						stack.push(x * y);
						break;
					case '/':
						stack.push(y / x);		
						break;
					
				}
				index++;
			}
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		String expression = "9+(3-1)*3+10/2+(3*90)-1";
		String postFix = toPostfix(expression);
		System.out.println(postFix);
		System.out.println(getPostfixVal(postFix));
		
	}
}
