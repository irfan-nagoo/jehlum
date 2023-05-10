package com.example.jehlum.thread.task.starter;

import com.example.jehlum.thread.task.ConcurrentReadWriteTask;
import com.example.jehlum.thread.task.ConcurrentTask;
import com.example.jehlum.thread.task.SynchronizedTask;
import com.example.jehlum.thread.task.WaitNotifyTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author irfan.nagoo
 */
public class ConcurrentTaskStarter {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // concurrency using synchronized block
        SynchronizedTask synTask = new SynchronizedTask();
        IntStream.range(0, 5).forEach(i -> executorService.execute(synTask));

        Thread.currentThread().join(1000);

        // concurrency using wait(), notify() object method
        WaitNotifyTask wnTask = new WaitNotifyTask();
        IntStream.range(0, 2).forEach(i -> executorService.execute(wnTask));

        // concurrency using Reentrant lock
        ConcurrentTask conTask = new ConcurrentTask();
        IntStream.range(0, 5).forEach(i -> executorService.execute(conTask));

        // concurrency using Reentrant read/write lock
        ConcurrentReadWriteTask rwTask = new ConcurrentReadWriteTask();
        IntStream.range(0, 5).forEach(i -> executorService.execute(rwTask));

        // shutdown
        executorService.shutdown();
    }
}
