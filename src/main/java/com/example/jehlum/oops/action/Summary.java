package com.example.jehlum.oops.action;

import com.example.jehlum.oops.Customer;
import com.example.jehlum.oops.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author irfan.nagoo
 */

public class Summary extends Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(Summary.class);

    public Summary(String command) {
        super(command);
    }

    @Override
    public void execute() {
        Set<Customer> customers = new CustomerRepository().getCustomers();
        LOGGER.info("Total Number of Customers: {}", customers.size());
        LOGGER.info("Customer Details: {}", customers);
    }
}
