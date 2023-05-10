package com.example.jehlum.apache.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author irfan.nagoo
 */

public class SemaphoreInitializer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreInitializer.class);

    private final Semaphore semaphore;
    private final AtomicLong counter;
    private final List<Long> dataList;

    public SemaphoreInitializer(@Nonnull Semaphore semaphore) {
        this.semaphore = semaphore;
        this.counter = new AtomicLong();
        this.dataList = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            LOGGER.info("Semaphore acquired!");
            dataList.add(counter.incrementAndGet());
            LOGGER.info("Exiting!");
            semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Long> getDataList() {
        return dataList;
    }
}
