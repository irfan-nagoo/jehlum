package com.example.jehlum.function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UpperCaseTest {

    @Test
    void apply() {
        UpperCase upperCase = a -> a.toUpperCase();
        assertEquals("ANTARCTICA", upperCase.apply("antarctica"));
    }
}