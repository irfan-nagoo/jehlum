package com.example.jehlum.guava.list;

import com.example.jehlum.guava.Customer;
import com.example.jehlum.guava.iterator.CustomerIterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author irfan.nagoo
 */
public class CustomerList extends ArrayList<Customer> {

    public Iterator<Customer> customerIterator() {
        return new CustomerIterator(this);
    }

    public void addCustomer(Customer customer) {
        this.add(customer);
    }

}
