package org.java.structure.stack;

/**
 * https://blog.csdn.net/javazejian/article/details/53362993
 * 符号匹配
 * Description:
 * @author zpp
 * @date   2018年6月11日
 */
public class CheckExpression {

	public static String isValid(String expstr) {
		// 创建栈
		LinkedStack<String> stack = new LinkedStack<>();

		int i = 0;
		while (i < expstr.length()) {
			char ch = expstr.charAt(i);
			i++;
			switch (ch) {
			case '(':
				stack.push(ch + "");// 左括号直接入栈
				break;
			case ')':
				if (stack.isEmpty() || !stack.pop().equals("(")) // 遇见右括号,左括号直接出栈
					return "(";
			}
		}
		// 最后检测是否为空,为空则检测通过
		if (stack.isEmpty())
			return "check pass!";
		else
			return "check exception!";
	}

	public static void main(String args[]) {
		String expstr = "((5-3)*8-2)";
		System.out.println(expstr + "  " + isValid(expstr));
	}
}