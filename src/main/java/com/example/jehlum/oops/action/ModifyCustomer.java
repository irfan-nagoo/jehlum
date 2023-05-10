package com.example.jehlum.oops.action;

import com.example.jehlum.oops.Customer;
import com.example.jehlum.oops.repository.CustomerRepository;

/**
 * @author irfan.nagoo
 */
public class ModifyCustomer extends Action {

    public ModifyCustomer(String command) {
        super(command);
    }

    @Override
    public void execute() {
        Customer customer = Customer.getInstance(command);
        new CustomerRepository().modifyCustomer(customer);
    }
}
