package com.example.jehlum.thread.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author irfan.nagoo
 */

public class CustomExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExecutorService.class);

    private final ExecutorService executorService;
    private final ForkJoinPool forkJoinPool;


    private CustomExecutorService(int nThreads) {
        this(Executors.newFixedThreadPool(nThreads), ForkJoinPool.commonPool());
    }

    private CustomExecutorService(ExecutorService executorService, ForkJoinPool forkJoinPool) {
        this.executorService = executorService;
        this.forkJoinPool = forkJoinPool;
    }

    public static CustomExecutorService newInstance(int nThreads) {
        return new CustomExecutorService(nThreads);
    }

    public static CustomExecutorService newInstance(ExecutorService executorService,
                                                    ForkJoinPool forkJoinPool) {
        return new CustomExecutorService(executorService, forkJoinPool);
    }

    public void run(Runnable runnable) {
        LOGGER.info("Submitting Runnable Task for execution");
        executorService.execute(runnable);
    }

    public Future<?> call(Callable<?> callable) {
        LOGGER.info("Submitting Callable Task for execution");
        return executorService.submit(callable);
    }

    public ForkJoinTask<?> callWithForkJoin(Callable<?> callable) {
        LOGGER.info("Submitting Callable Task for ForkJoin execution");
        return forkJoinPool.submit(callable);
    }

    public void shutdown() {
        LOGGER.info("Shutting down...");
        executorService.shutdown();
        forkJoinPool.shutdown();
        LOGGER.info("Shutdown Completed!");
    }

}
