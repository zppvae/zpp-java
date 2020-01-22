package org.java.basic.code;

/**
 * 初始化阶段是执行类构造器<clinit>（）方法的过程。类构造器<clinit>（）方法是由编译器
 * 自动收藏类中的所有类变量的赋值动作和静态语句块(static块)中的语句合并产生，代码从上往下执行。
 * 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化
 */
public class CodeBlock {
    {
        System.out.println("普通代码块");
    }

    //多个对象只会初始化一次
    static {
        System.out.println("静态代码块");
        a = 700;
    }

    // a的值与此声明的位置有关
    private static int a = 500;

    public CodeBlock (){
        System.out.println("构造方法");
    }

    public static void main(String[] args){
        CodeBlock c1 = new CodeBlock();
        CodeBlock c2 = new CodeBlock();
        System.out.println(a);
    }
}
