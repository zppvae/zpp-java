package org.java.model.singleton;


import org.java.annotations.ThreadSafe;

/**
 * 懒汉模式 （双重检查），推荐面试使用
 */
@ThreadSafe
public class Singleton6 {

    private Singleton6() {}

    /**
     * volatile：
     * 1、新建对象实际上有3个步骤
     * 2、重排序会带来NPE
     * 3、防止重排序
     *
     * 比如：instance = new Singleton6();
     * public Singleton6(){
     *     field1 =
     *     field2 =
     * }
     *
     * 新建对象步骤：1、new Singleton6() 2、初始化field1 2参数值  3、指向引用instance
     *
     * 假如线程1执行时发生重排序，步骤顺序为132，则线程2使用filed1参数时会出现NPE问题
     */
    private volatile static Singleton6 instance = null;

    public static Singleton6 getInstance() {
        if (instance == null) {
            synchronized (Singleton6.class) {
                //防止多线程同时竞争锁的情况多次创建
                if (instance == null) {
                    instance = new Singleton6();
                }
            }
        }
        return instance;
    }
}
