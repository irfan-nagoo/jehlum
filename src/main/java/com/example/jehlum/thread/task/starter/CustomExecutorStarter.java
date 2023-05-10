package com.example.jehlum.thread.task.starter;

import com.example.jehlum.thread.MyCallable;
import com.example.jehlum.thread.MyRunnable;
import com.example.jehlum.thread.executor.CustomExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * @author irfan.nagoo
 */
public class CustomExecutorStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExecutorService.class);

    public static void main(String[] args) {

        // custom executor service
        CustomExecutorService executorService = CustomExecutorService.newInstance(5);

        // try runnable
        IntStream.range(0, 10).forEach(i -> executorService.run(new MyRunnable()));

        // try callable
        Future<?> future = executorService.call(new MyCallable());
        try {
            LOGGER.info("Value Returned: {}", future.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Thread Execution exception", e);
        }

        // try callable with ForkJoin
        ForkJoinTask<?> fjFutureTask = executorService.callWithForkJoin(new MyCallable());
        try {
            LOGGER.info("ForkJoin Value Returned: {}", fjFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Thread Execution exception", e);
        }

        // shutdown
        executorService.shutdown();
    }
}
