package com.example.jehlum.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irfan.nagoo
 */
public class WaitNotifyTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WaitNotifyTask.class);

    private static int counter;
    private final Object object = new Object();

    @Override
    public void run() {
        while (counter < 10) {
            synchronized (object) {
                counter++;
                if (counter % 2 == 0) {
                    LOGGER.info("Counter Value: {} [{}]", counter, Thread.currentThread().getName());
                    object.notify();
                } else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        LOGGER.info("Exception occurred", e);
                    }
                }
            }
        }
    }
}