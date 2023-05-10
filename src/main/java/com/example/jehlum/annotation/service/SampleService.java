package com.example.jehlum.annotation.service;

import com.example.jehlum.annotation.Matcher;
import com.example.jehlum.annotation.PerformanceLogger;
import com.example.jehlum.annotation.interceptor.CGLibMethodInterceptor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author irfan.nagoo
 */

public class SampleService {

    private static SampleService INSTANCE;

    /**
     * Create CGLIB proxy and return the proxy instance
     *
     * @return Proxy SampleService instance
     */
    public static SampleService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SampleService();
        }
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleService.class);
        enhancer.setCallback(new CGLibMethodInterceptor<>(INSTANCE, Matcher.class, PerformanceLogger.class));
        return (SampleService) enhancer.create();
    }

    @PerformanceLogger
    public Integer getMax(List<Integer> list) {
        if (!CollectionUtils.isEmpty(list)) {
            return list.stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .orElse(0);
        } else {
            throw new IllegalArgumentException("List is null or empty");
        }
    }

    @PerformanceLogger
    public Integer getMin(List<Integer> list) {
        if (!CollectionUtils.isEmpty(list)) {
            return list.stream()
                    .mapToInt(Integer::intValue)
                    .min()
                    .orElse(-1);
        } else {
            throw new IllegalArgumentException("List is null or empty");
        }
    }

}
