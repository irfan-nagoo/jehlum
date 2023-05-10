package com.example.jehlum.annotation.interceptor;

import com.example.jehlum.annotation.finder.ClassFinder;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * This is a JDK Dynamic proxy invocation handler. When the method calls happen on the related proxy object,
 * this handler intercepts those method calls, applies the configured aspect (e.g. performance logging)
 * and then delegates the method call to the actual target object. This is similar to how Spring @Aspect annotation
 * works
 *
 * @author irfan.nagoo
 */
public class DynamicInvocationHandler<T> implements InvocationHandler {

    private static final String BASE_PACKAGE = "com.example.jehlum";

    private final T targetObject;
    private final Class<? extends Annotation> matcherAnnotationClass;
    private final Class<? extends Annotation> targetAnnotationClass;

    /**
     * Creates an instance of Dynamic handler
     *
     * @param targetObject           Actually target instance
     * @param matcherAnnotationClass Matcher annotation which has aspect implementation
     * @param targetAnnotationClass  Target annotation applied on actual service
     */
    public DynamicInvocationHandler(T targetObject, Class<? extends Annotation> matcherAnnotationClass,
                                    Class<? extends Annotation> targetAnnotationClass) {
        this.targetObject = targetObject;
        this.matcherAnnotationClass = matcherAnnotationClass;
        this.targetAnnotationClass = targetAnnotationClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
                return matchingMethod.invoke(loggerMatcher.newInstance(), targetObject, method, args);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
        return method.invoke(targetObject, args);
    }
}
