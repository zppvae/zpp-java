package org.java.basic.generic;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 *
 *
 */
@Slf4j
public class FruitGenerator implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }

    /**
     * 泛型通配符
     *
     * ？代替具体的类型实参
     * @param obj
     */
    public void getVal(GenericDemo<?> obj){
        log.info("泛型测试 key value is {}",obj.getKey());
    }

    /**
     * 泛型方法
     *
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     */
    public <T> T genericMethod(Class<T> tClass)throws InstantiationException ,
            IllegalAccessException{
        T instance = tClass.newInstance();
        return instance;
    }
}