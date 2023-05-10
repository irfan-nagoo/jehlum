package com.example.jehlum.oops.executor;

import com.example.jehlum.oops.action.Action;
import org.springframework.lang.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/**
 * @author irfan.nagoo
 */

public class CommandExecutor implements Runnable {

    private final BlockingQueue<Action> taskQueue;
    private final ExecutorService executor;

    public CommandExecutor(@NonNull BlockingQueue<Action> commandQueue, @NonNull ExecutorService executor) {
        this.taskQueue = commandQueue;
        this.executor = executor;
        this.executor.execute(this);
    }

    public void execute(Action action) {
        if (!taskQueue.offer(action)) {
            throw new RuntimeException("Action could not be executed");
        }
    }

    public void run() {
        for (; ; ) {
            try {
                Action action = taskQueue.take();
                executor.submit(action::execute).get();
                if (taskQueue.isEmpty()) {
                    break;
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
