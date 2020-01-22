package org.java.util;

import lombok.extern.slf4j.Slf4j;
import org.java.annotations.AppleFruid;
import org.java.annotations.FruidProvider;

import java.lang.reflect.Field;

/**
 * @author zpp
 * @date 2020/1/2 17:49
 */
@Slf4j
public class AnnotationUtils {

    public static void printAnnotation(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(FruidProvider.class)) {
                FruidProvider fp = f.getAnnotation(FruidProvider.class);
                log.info("{}, {}",fp.id(),fp.name());
            }
        }
    }

    public static void main(String[] args){
        printAnnotation(AppleFruid.class);
    }
}
