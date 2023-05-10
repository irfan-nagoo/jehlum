package com.example.jehlum.apache.concurrency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
class CustomThreadFactoryTest {

    @Test
    void builder() {
        CustomThreadFactory threadFactory = CustomThreadFactory.builder()
                .setNamePattern("custom-thread-pool")
                .setPriority(Thread.MIN_PRIORITY)
                .setDaemon(true)
                .setThreadGroup(new ThreadGroup("CustomThreadGroup"))
                .build();

        ExecutorService executorService = Executors.newFixedThreadPool(5, threadFactory);
        executorService.execute(() -> assertTrue(Thread.currentThread().getName().contains("custom-thread-pool")));
        executorService.execute(() -> assertEquals(Thread.MIN_PRIORITY, Thread.currentThread().getPriority()));
        executorService.execute(() -> assertTrue(Thread.currentThread().isDaemon()));
    }

}