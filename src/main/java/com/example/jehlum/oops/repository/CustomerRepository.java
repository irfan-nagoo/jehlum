package com.example.jehlum.oops.repository;

import com.example.jehlum.oops.Customer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author irfan.nagoo
 */

public class CustomerRepository {

    private static final Set<Customer> CUSTOMERS = new HashSet<>();

    public void addCustomer(Customer customer) {
        CUSTOMERS.add(customer);
    }

    public void modifyCustomer(Customer customer) {
        CUSTOMERS.remove(customer);
        CUSTOMERS.add(customer);
    }

    public Set<Customer> getCustomers() {
        return CUSTOMERS;
    }

    public void deleteCustomer(Customer customer) {
        CUSTOMERS.remove(customer);
    }

}


