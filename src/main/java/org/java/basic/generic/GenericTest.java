package org.java.basic.generic;

/**
 * @author zpp
 * @date 2019/8/2 18:21
 */
public class GenericTest {

    /**
     * 泛型方法
     * @param test
     * @param <T>
     * @return
     */
    public <T> T getName(GenericDemo<T> test){
        return test.getKey();
    }

    /**
     * 泛型方法与可变参数
     * @param args
     * @param <T>
     */
    public <T> void printMsg( T... args){
        for(T t : args){
            //
        }
    }

    /**
     * 传入的类型实参必须是指定类型（Number）的子类型
     * @param obj
     */
    public void showKeyValue(GenericDemo<? extends Number> obj){
        //
    }

    /**
     * 在泛型方法中添加上下边界限制的时候，必须在权限声明与返回值之间的<T>上添加上下边界，即在泛型声明的时候添加
     */
    public <T extends Number> T showKeyName(GenericDemo<T> container){
        System.out.println("container key :" + container.getKey());
        T test = container.getKey();
        return test;
    }

}
