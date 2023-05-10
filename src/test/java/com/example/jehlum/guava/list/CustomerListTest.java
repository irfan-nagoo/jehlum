package com.example.jehlum.guava.list;

import com.example.jehlum.guava.Customer;
import com.example.jehlum.guava.list.CustomerList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomerListTest {

    @Test
    void addCustomer() {
        CustomerList customerList = new CustomerList();
        customerList.addCustomer(new Customer("TestName1", "TestCity1"));
        customerList.addCustomer(new Customer("TestName2", "TestCity2"));
        customerList.addCustomer(new Customer("TestName3", "TestCity3"));
        Iterator<Customer> itr = customerList.customerIterator();
        int i = 1;
        while (itr.hasNext()) {
            Customer cust = itr.next();
            assertEquals("TestName" + i, cust.getName());
            assertEquals("TestCity" + i, cust.getCity());
            i++;
        }
    }

}