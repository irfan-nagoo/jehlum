package com.example.jehlum.apache.concurrency;

import org.apache.commons.lang3.concurrent.TimedSemaphore;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TimedSemaphoreInitializerTest {

    @Test
    void getCount() throws InterruptedException, ExecutionException {

        // init timed semaphore
        TimedSemaphore semaphore = new TimedSemaphore(1000, TimeUnit.MILLISECONDS,
                1);
        TimedSemaphoreInitializer initializer1 = new TimedSemaphoreInitializer(semaphore);
        TimedSemaphoreInitializer initializer2 = new TimedSemaphoreInitializer(semaphore);
        TimedSemaphoreInitializer initializer3 = new TimedSemaphoreInitializer(semaphore);

        // completable future with provided executors
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletableFuture<Long> completableFuture1 = CompletableFuture.supplyAsync(initializer1::getCount, executor);
        CompletableFuture<Long> completableFuture2 = CompletableFuture.supplyAsync(initializer2::getCount, executor);
        CompletableFuture<Long> completableFuture3 = CompletableFuture.supplyAsync(initializer3::getCount, executor);

        // let all task complete, main thread waits
        CompletableFuture.allOf(completableFuture1, completableFuture2, completableFuture3).join();

        // since the tasks are executed in parallel, result of each task is not certain
        assertTrue(completableFuture1.get() >= 1 && completableFuture1.get() <= 3);
        assertTrue(completableFuture2.get() >= 1 && completableFuture2.get() <= 3);
        assertTrue(completableFuture3.get() >= 1 && completableFuture3.get() <= 3);
    }
}