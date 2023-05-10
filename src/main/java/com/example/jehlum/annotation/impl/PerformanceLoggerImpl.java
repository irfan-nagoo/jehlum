package com.example.jehlum.annotation.impl;

import com.example.jehlum.annotation.Matcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author irfan.nagoo
 */

@Matcher
public class PerformanceLoggerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceLoggerImpl.class);

    public Object execute(Object object, Method method, Object... args) {
        Object result = null;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            result = method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Error occurred while method invocation: ", e);
        }
        stopWatch.stop();
        LOGGER.info("Execution time for the method [{}] was [{}] ms", method.getName(),
                stopWatch.getTotalTimeMillis());
        return result;
    }
}
