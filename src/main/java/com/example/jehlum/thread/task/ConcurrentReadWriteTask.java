package com.example.jehlum.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author irfan.nagoo
 */
public class ConcurrentReadWriteTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentReadWriteTask.class);

    private static int counter;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Override
    public void run() {
        rwLock.writeLock().lock();
        counter++;
        if (counter % 2 == 0) {
            rwLock.readLock().lock();
            rwLock.writeLock().unlock();
            LOGGER.info("Counter Value: {} [{}]", counter, Thread.currentThread().getName());
            rwLock.readLock().unlock();
        } else {
            rwLock.writeLock().unlock();
        }
    }
}
