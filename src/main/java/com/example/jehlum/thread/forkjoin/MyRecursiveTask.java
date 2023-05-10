package com.example.jehlum.thread.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author irfan.nagoo
 */
public class MyRecursiveTask extends RecursiveTask<Integer> {

    private final List<Integer> list;

    public MyRecursiveTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected Integer compute() {
        if (list.size() > 2) {
            List<MyRecursiveTask> myMyRecursiveTaskList = createSubTasks();
            for (MyRecursiveTask task : myMyRecursiveTaskList) {
                task.fork();
            }

            int sum = 0;
            for (MyRecursiveTask task : myMyRecursiveTaskList) {
                sum += task.join();
            }
            return sum;
        } else {
            return list.stream().mapToInt(Integer::intValue).sum();
        }
    }

    List<MyRecursiveTask> createSubTasks() {
        List<MyRecursiveTask> myMyRecursiveTaskList = new ArrayList<>();
        myMyRecursiveTaskList.add(new MyRecursiveTask(list.subList(0, list.size() / 2)));
        myMyRecursiveTaskList.add(new MyRecursiveTask(list.subList(list.size() / 2, list.size())));
        return myMyRecursiveTaskList;
    }

}