package com.example.jehlum.thread.task.starter;

import com.example.jehlum.thread.forkjoin.MyRecursiveAction;
import com.example.jehlum.thread.forkjoin.MyRecursiveTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author irfan.nagoo
 */
public class ForkJoinSummationStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoinSummationStarter.class);

    public static void main(String[] args) {

        // create fork join pool
        ForkJoinPool pool = ForkJoinPool.commonPool();

        // run my recursive action to compute summation
        MyRecursiveAction myRecursiveAction = new MyRecursiveAction(Arrays.asList(1, 2, 3, 4));
        pool.invoke(myRecursiveAction);

        // run my recursive task to compute and return summation
        MyRecursiveTask myRecursiveTask = new MyRecursiveTask(Arrays.asList(1, 2, 3, 4, 5, 6));
        int result = pool.invoke(myRecursiveTask);
        LOGGER.info("Sum: {}", result);

        // await for shutdown to let the tasks finish
        try {
            pool.awaitTermination(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("The Thread is interrupted: ", e);
        }
    }


}
