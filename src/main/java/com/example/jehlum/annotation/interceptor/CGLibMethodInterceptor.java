package com.example.jehlum.annotation.interceptor;

import com.example.jehlum.annotation.finder.ClassFinder;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * This is a CGLIB interceptor. When the method calls happen on the related proxy object,
 * this interceptor intercepts those method calls, applies the configured aspect (e.g. performance logging)
 * and then delegates the method call to the actual target object. This is similar to how Spring @Aspect annotation
 * works
 *
 * @author irfan.nagoo
 */

public class CGLibMethodInterceptor<T> implements MethodInterceptor {

    private static final String BASE_PACKAGE = "com.example.jehlum";

    private final T targetObject;
    private final Class<? extends Annotation> matcherAnnotationClass;
    private final Class<? extends Annotation> targetAnnotationClass;

    /**
     * Creates an instance of CGLIB interceptor
     *
     * @param targetObject           Actually target instance
     * @param matcherAnnotationClass Matcher annotation which has aspect implementation
     * @param targetAnnotationClass  Target annotation applied on actual service
     */
    public CGLibMethodInterceptor(T targetObject, Class<? extends Annotation> matcherAnnotationClass,
                                  Class<? extends Annotation> targetAnnotationClass) {
        this.targetObject = targetObject;
        this.matcherAnnotationClass = matcherAnnotationClass;
        this.targetAnnotationClass = targetAnnotationClass;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // find matcher (action class) in the provided package
        List<Class<?>> classList = new ClassFinder(BASE_PACKAGE).find();
        Class<?> loggerMatcher = classList.stream()
                .filter(c -> c.getAnnotation(matcherAnnotationClass) != null)
                .findAny()
                .orElse(Object.class);

        // find targeted methods with the given annotation in the targeted service
        Annotation perfAnnotation = method.getAnnotation(targetAnnotationClass);
        if (null != perfAnnotation) {
            Method matchingMethod = loggerMatcher.getDeclaredMethods()[0];
            try {
                // execute the action method of the matcher class
                return matchingMethod.invoke(loggerMatcher.newInstance(), targetObject, method, args);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
        return methodProxy.invokeSuper(proxy, args);
    }
}
