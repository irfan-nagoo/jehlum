package com.example.jehlum.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation does the performance logging of the targeted public method
 *
 * @author irfan.nagoo
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PerformanceLogger {
    boolean enablePrivate() default false;
}
