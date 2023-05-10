package com.example.jehlum.guava.listenable;

import com.google.common.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @author irfan.nagoo
 */

public class ListenableFutureTask<V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenableFutureTask.class);

    private final ListeningExecutorService service;

    public ListenableFutureTask() {
        service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
    }

    /**
     * Runs a callable task with a Callback feature giving the execution status
     *
     * @param callable - callable task
     * @return - callable value
     * @throws ExecutionException   - exception while execution
     * @throws InterruptedException - if the thread gets interrupted
     */
    public V runWithCallback(Callable<V> callable) throws ExecutionException, InterruptedException {
        ListenableFuture<V> listenableFuture = service.submit(callable);
        Futures.addCallback(listenableFuture, new FutureCallback<V>() {
            @Override
            public void onSuccess(V result) {
                LOGGER.info("The value returned is {}", result);
            }

            @Override
            public void onFailure(@NonNull Throwable t) {
                LOGGER.error("Error occurred while execution", t);
            }
        }, service);
        return listenableFuture.get();
    }

}
