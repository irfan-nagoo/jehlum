package com.example.jehlum.thread;

import com.example.jehlum.thread.executor.CustomExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @author irfan.nagoo
 */
public class MyCallable implements Callable<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExecutorService.class);

    @Override
    public Integer call() {
        LOGGER.info("Running MyCallable: {}", Thread.currentThread().getName());
        return 10;
    }
}
