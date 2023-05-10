package com.example.jehlum.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/**
 * @author irfan.nagoo
 */
public class CustomThreadPool {

    private final Thread[] threads;
    private final BlockingQueue<Runnable> queue;
    private final AtomicBoolean isShutDownRequested;

    public CustomThreadPool(int nThreads) {
        threads = new Thread[nThreads];
        queue = new LinkedBlockingQueue<>();
        isShutDownRequested = new AtomicBoolean(false);

        // initialize thread pool
        IntStream.range(0, nThreads).forEach(i -> {
            threads[i] = new CustomThread(queue, isShutDownRequested, i + 1);
            threads[i].start();
        });

    }

    public static CustomThreadPool createThreadPool(int nThreads) {
        return new CustomThreadPool(nThreads);
    }

    public void submitTask(Runnable runnable) {
        if (!this.isShutDownRequested.get()) {
            if (!this.queue.offer(runnable)) {
                throw new RuntimeException("Cannot submit new task");
            }
        } else {
            throw new RuntimeException("Shutdown requested, cannot execute new tasks");
        }
    }

    public void shutDown() {
        this.isShutDownRequested.set(true);
    }

    private static final class CustomThread extends Thread {

        private final BlockingQueue<Runnable> queue;
        private final AtomicBoolean isShutDownRequested;

        public CustomThread(BlockingQueue<Runnable> queue, AtomicBoolean isShutDownRequested, int number) {
            this.queue = queue;
            this.isShutDownRequested = isShutDownRequested;
            this.setName("raptor-thread-" + number);
        }

        @Override
        public void run() {
            while (!isShutDownRequested.get() || !this.queue.isEmpty()) {
                Runnable runnable;
                if ((runnable = queue.poll()) != null) {
                    runnable.run();
                }
            }
        }
    }
}
