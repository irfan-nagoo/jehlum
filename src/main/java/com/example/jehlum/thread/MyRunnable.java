package com.example.jehlum.thread;

import com.example.jehlum.thread.executor.CustomExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irfan.nagoo
 */
public class MyRunnable implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExecutorService.class);

    @Override
    public void run() {
        LOGGER.info("Running MyRunnable: {}", Thread.currentThread().getName());
    }
}