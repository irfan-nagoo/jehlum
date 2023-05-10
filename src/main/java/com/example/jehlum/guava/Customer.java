package com.example.jehlum.guava;

/**
 * @author irfan.nagoo
 */
public class Customer {

    private final String name;
    private final String city;

    public Customer(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

}
