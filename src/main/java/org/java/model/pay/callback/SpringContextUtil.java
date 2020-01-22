package org.java.model.pay.callback;

public class SpringContextUtil {

    public static UnionPayCallbackTemplate getBean(String beanId){
        return new UnionPayCallbackTemplate();
    }

}
