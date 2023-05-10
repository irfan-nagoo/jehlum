package com.example.jehlum.guava;

/**
 * @author irfan.nagoo
 */
public class Company {

    private final String name;
    private final String address;

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Company(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String name;
        private String address;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Company build() {
            return new Company(this);
        }

    }


}
