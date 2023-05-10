package com.example.jehlum.thread.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @author irfan.nagoo
 */
public class MyRecursiveAction extends RecursiveAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRecursiveAction.class);

    private final List<Integer> list;

    public MyRecursiveAction(List<Integer> list) {
        this.list = list;
    }


    @Override
    protected void compute() {
        if (list.size() > 2) {
            List<MyRecursiveAction> myRecursiveActions = createSubTasks();
            for (MyRecursiveAction task : myRecursiveActions) {
                task.fork();
            }
            merge(myRecursiveActions);
        } else {
            LOGGER.info("Partial Sum: {}", list.stream().mapToInt(Integer::valueOf).sum());
        }
    }

    private List<MyRecursiveAction> createSubTasks() {
        List<MyRecursiveAction> myRecursiveActionList = new ArrayList<>();
        myRecursiveActionList.add(new MyRecursiveAction(list.subList(0, list.size() / 2)));
        myRecursiveActionList.add(new MyRecursiveAction(list.subList(list.size() / 2, list.size())));
        return myRecursiveActionList;
    }

    private static void merge(List<MyRecursiveAction> myRecursiveActions) {
        int sum = 0;
        for (MyRecursiveAction task : myRecursiveActions) {
            task.join();
            sum += task.list.stream().mapToInt(Integer::valueOf).sum();
        }
        LOGGER.info("Final Sum: {}", sum);
    }
}