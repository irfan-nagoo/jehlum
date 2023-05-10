package com.example.jehlum.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is a annotation denotes the matcher class which has the implementation
 * for a certain aspect (e.g. performance logging)
 *
 * @author irfan.nagoo
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Matcher {
}
