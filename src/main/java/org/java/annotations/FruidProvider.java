package org.java.annotations;

import java.lang.annotation.*;

/**
 * @author zpp
 * @date 2020/1/2 17:47
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruidProvider {

    public int id() default 0;

    public String name() default "供应商";
}
