package com.example.jehlum.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irfan.nagoo
 */
public class SynchronizedTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizedTask.class);

    private static int counter;

    @Override
    public void run() {
        synchronized (this) {
            counter++;
            if (counter % 2 == 0) {
                LOGGER.info("Counter Value: {} [{}]", counter, Thread.currentThread().getName());
            }
        }
    }
}