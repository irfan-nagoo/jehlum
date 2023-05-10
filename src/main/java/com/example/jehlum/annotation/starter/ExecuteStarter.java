package com.example.jehlum.annotation.starter;

import com.example.jehlum.annotation.Execute;
import com.example.jehlum.annotation.finder.ClassFinder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author irfan.nagoo
 */

public class ExecuteStarter {

    private final String packageName;

    public ExecuteStarter(String packageName) {
        this.packageName = packageName;
    }

    public void execute() {
        List<Class<?>> classList = new ClassFinder(packageName).find();
        for (Class<?> clazz : classList) {
            Object object = null;
            for (Method method : clazz.getDeclaredMethods()) {
                Annotation annotation = method.getAnnotation(Execute.class);
                if (null != annotation && clazz.getDeclaredConstructors().length > 0) {
                    try {
                        if (object == null) {
                            Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
                            constructor.setAccessible(true);
                            object = constructor.newInstance();
                        }
                        method.invoke(object);
                    } catch (IllegalAccessException | InvocationTargetException
                             | InstantiationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new ExecuteStarter("com.example.jehlum.annotation.service").execute();
    }

}
