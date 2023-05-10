package com.example.jehlum.oops;

import java.util.Objects;

/**
 * @author irfan.nagoo
 */


public class Customer {

    private final Long id;
    private String name;
    private Integer age;
    private String city;

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(Long id, String name, Integer age, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public static Customer getInstance(String command) {
        String[] input = command.split("[\\s+]");
        if (input.length == 5) {
            return new Customer(Long.parseLong(input[1]), input[2], Integer.parseInt(input[3]), input[4]);
        } else {
            return new Customer(Long.parseLong(input[1]));
        }

    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}
