package com.example.jehlum.annotation.service;

import com.example.jehlum.annotation.Execute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irfan.nagoo
 */
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Execute
    public void printName() {
        LOGGER.info("Executing Employee.printName()...");
    }

    @Execute
    public void getName() {
        LOGGER.info("Executing Employee.getName()...");
    }

    public void getSalary() {
        LOGGER.info("Executing Employee.getSalary()...");
    }
}
