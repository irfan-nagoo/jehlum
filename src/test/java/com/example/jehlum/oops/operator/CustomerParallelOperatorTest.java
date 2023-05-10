package com.example.jehlum.oops.operator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@ExtendWith(MockitoExtension.class)
class CustomerParallelOperatorTest {

    @Test
    void start_Basic() {
        CustomerParallelOperator operator = new CustomerParallelOperator(
                "src/test/resources/customer/customer_input_1.txt");
        assertDoesNotThrow(operator::start);
    }

    @Test
    void start_Advanced() {
        CustomerParallelOperator operator = new CustomerParallelOperator(
                "src/test/resources/customer/customer_input_2.txt");
        assertDoesNotThrow(operator::start);
    }
}