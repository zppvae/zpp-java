package org.java.basic.innerclass;

/**
 * 外部类
 * @author zpp
 * @date 2020/1/2 17:59
 */
public class Outer {
    private static int a = 1;

    private int b = 2;

    /**
     * 静态内部类
     */
    public static class StaticInner {
        public void print(){
            System.out.println(a);
        }
    }

    /**
     * 成员内部类
     *
     * 成员内部类不能定义静态方法和静态变量
     */
    public class MemberInner {
        public void print(){
            System.out.println(b + "-" + a);
        }
    }

    /**
     * 局部内部类
     * @param c
     */
    public void test(final int c) {
        final int d = 1;
        class Inner {
            public void print() {
                System.out.println(c);
            }
        }
    }


    public static void main(String[] args){
//        Outer.StaticInner inner = new Outer.StaticInner();

    }
}
