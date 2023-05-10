package com.example.jehlum.apache.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SemaphoreInitializerTest {

    @Test
    void run() throws InterruptedException {
        SemaphoreInitializer initializer = new SemaphoreInitializer(new Semaphore(1));
        new Thread(initializer).start();
        new Thread(initializer).start();
        new Thread(initializer).start();
        Thread.currentThread().join(1000);
        assertEquals(3, initializer.getDataList().size());
        assertEquals(1, initializer.getDataList().get(0));
        assertEquals(3, initializer.getDataList().get(2));
    }
}