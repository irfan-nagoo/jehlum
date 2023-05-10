package com.example.jehlum.oops.operator;

import com.example.jehlum.oops.action.AddCustomer;
import com.example.jehlum.oops.action.DeleteCustomer;
import com.example.jehlum.oops.action.ModifyCustomer;
import com.example.jehlum.oops.action.Summary;
import com.example.jehlum.oops.constants.ActionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author irfan.nagoo
 */

public class CustomerSerialOperator {

    private final String inputFilePath;

    public CustomerSerialOperator(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public void start() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFilePath));
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            ActionType action = ActionType.getAction(command);
            switch (action) {
                case ADD:
                    new AddCustomer(command).execute();
                    break;
                case MODIFY:
                    new ModifyCustomer(command).execute();
                    break;
                case DELETE:
                    new DeleteCustomer(command).execute();
                    break;
                case SUMMARY:
                    new Summary(command).execute();
            }
        }
    }
}
