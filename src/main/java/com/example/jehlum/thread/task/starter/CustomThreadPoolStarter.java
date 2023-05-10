package com.example.jehlum.thread.task.starter;

import com.example.jehlum.thread.pool.CustomThreadPool;

/**
 * @author irfan.nagoo
 */
public class CustomThreadPoolStarter {

    public static void main(String[] args) {
        // initialize thread pool
        CustomThreadPool pool = CustomThreadPool.createThreadPool(10);

        // run task
        pool.submitTask(() -> System.out.println("Task 1"));
        pool.submitTask(() -> System.out.println("Task 2"));

        // shutdown
        pool.shutDown();
    }
}
