package com.example.jehlum.guava.iterator;

import com.example.jehlum.guava.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * @author irfan.nagoo
 */
public class CustomerIterator implements Iterator<Customer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerIterator.class);

    private final List<Customer> customers;

    public CustomerIterator(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public boolean hasNext() {
        return !this.customers.isEmpty();
    }

    @Override
    public Customer next() {
        LOGGER.info("Getting next Customer");
        return this.customers.remove(0);
    }

}