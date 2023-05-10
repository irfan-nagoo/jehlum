package com.example.jehlum.guava;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CompanyTest {

    @Test
    void builder() {
        Company company = Company.builder()
                .setName("ACMA")
                .setAddress("New York")
                .build();
        assertEquals("ACMA", company.getName());
        assertEquals("New York", company.getAddress());
    }
}