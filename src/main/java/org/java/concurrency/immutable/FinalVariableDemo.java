package org.java.concurrency.immutable;

/**
 * final变量
 */
public class FinalVariableDemo {

    private static final Person person = null;

    private final int a;

    void testFinal() {
       final int b = 7;
       int c =b;
    }
//    public FinalVariableDemo() {
//    }

//    public FinalVariableDemo(int a) {
//        this.a = a;
//    }

    {
        a = 10;
    }

    private static final int m;

    /**
     * 类中的 static final 属性
     */
    static {
        m = 7;
    }
}
