package com.example.jehlum.oops.operator;

import com.example.jehlum.oops.action.AddCustomer;
import com.example.jehlum.oops.action.DeleteCustomer;
import com.example.jehlum.oops.action.ModifyCustomer;
import com.example.jehlum.oops.action.Summary;
import com.example.jehlum.oops.constants.ActionType;
import com.example.jehlum.oops.executor.CommandExecutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;

/**
 * @author irfan.nagoo
 */

public class CustomerParallelOperator {

    private final String inputFilePath;

    public CustomerParallelOperator(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public void start() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFilePath));
        CommandExecutor commandExecutor = new CommandExecutor(new ArrayBlockingQueue<>(100),
                Executors.newFixedThreadPool(10));
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            ActionType action = ActionType.getAction(command);
            switch (action) {
                case ADD:
                    commandExecutor.execute(new AddCustomer(command));
                    break;
                case MODIFY:
                    commandExecutor.execute(new ModifyCustomer(command));
                    break;
                case DELETE:
                    commandExecutor.execute(new DeleteCustomer(command));
                    break;
                case SUMMARY:
                    commandExecutor.execute(new Summary(command));
            }
        }
    }
}
