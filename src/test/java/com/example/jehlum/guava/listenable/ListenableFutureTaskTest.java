package com.example.jehlum.guava.listenable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ListenableFutureTaskTest {

    @Test
    void runWithCallback() throws ExecutionException, InterruptedException {
        ListenableFutureTask<String> listenableFutureTask = new ListenableFutureTask<>();
        String result = listenableFutureTask.runWithCallback(() -> {
            int i = 0;
            do {
                i++;
            } while (i <= 10);
            return "Hello";
        });
        assertEquals("Hello", result);
    }

}