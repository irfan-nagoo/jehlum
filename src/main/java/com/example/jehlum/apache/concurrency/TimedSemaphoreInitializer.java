package com.example.jehlum.apache.concurrency;

import org.apache.commons.lang3.concurrent.TimedSemaphore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author irfan.nagoo
 */

public class TimedSemaphoreInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreInitializer.class);
    private static final AtomicLong counter = new AtomicLong(0);

    private final TimedSemaphore timedSemaphore;

    public TimedSemaphoreInitializer(TimedSemaphore timedSemaphore) {
        this.timedSemaphore = timedSemaphore;
    }

    public Long getCount() {
        try {
            timedSemaphore.acquire();
            LOGGER.info("Semaphore acquired!");
            return counter.incrementAndGet();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /*TimedSemaphore semaphore = new TimedSemaphore(3000, TimeUnit.MILLISECONDS, 1);
        TimedSemaphoreInitializer initializer1 = new TimedSemaphoreInitializer(semaphore);
        TimedSemaphoreInitializer initializer2 = new TimedSemaphoreInitializer(semaphore);
        TimedSemaphoreInitializer initializer3 = new TimedSemaphoreInitializer(semaphore);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(initializer1);
        executor.execute(initializer2);
        executor.execute(initializer3);
        executor.shutdown();
        if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }*/
    }
}
