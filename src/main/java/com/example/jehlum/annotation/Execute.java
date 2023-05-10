package com.example.jehlum.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This example annotation is meant to execute a particular method in class
 * just like @Test in Junit framework
 *
 * @author irfan.nagoo
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Execute {
    String value() default "";
}
