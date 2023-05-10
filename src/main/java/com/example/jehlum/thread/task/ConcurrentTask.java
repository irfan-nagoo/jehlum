package com.example.jehlum.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author irfan.nagoo
 */
public class ConcurrentTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentTask.class);

    private static int counter;
    private final Lock reLock = new ReentrantLock();

    @Override
    public void run() {
        reLock.lock();
        try {
            counter++;
            if (counter % 2 == 0) {
                LOGGER.info("Counter Value: {} [{}]", counter, Thread.currentThread().getName());
            }
        } finally {
            reLock.unlock();
        }
    }
}