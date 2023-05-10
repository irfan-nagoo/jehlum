package com.example.jehlum.annotation.service;

import com.example.jehlum.annotation.Matcher;
import com.example.jehlum.annotation.PerformanceLogger;
import com.example.jehlum.annotation.interceptor.DynamicInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.Optional;

/**
 * @author irfan.nagoo
 */
public class DepartmentServiceImpl implements DepartmentService {

    private static DepartmentService INSTANCE;

    /**
     * Create JDK Dynamic proxy and return the proxy instance
     *
     * @return Proxy DepartmentServiceImpl instance
     */
    public static DepartmentService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DepartmentServiceImpl();
        }
        return (DepartmentService) Proxy.newProxyInstance(DepartmentService.class.getClassLoader(),
                new Class[]{DepartmentService.class}, new DynamicInvocationHandler<>(INSTANCE,
                        Matcher.class, PerformanceLogger.class));
    }

    @Override
    public String printDepartmentName(String name) {
        return Optional.ofNullable(name).orElse("").toUpperCase();
    }
}
