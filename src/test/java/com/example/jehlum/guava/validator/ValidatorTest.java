package com.example.jehlum.guava.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidatorTest {

    @Test
    void validate_Object() {
        boolean output = Optional.of(new Object())
                .map(ObjectValidator::new)
                .get()
                .validate();
        assertTrue(output);
    }

    @Test
    void validate_String() {
        boolean output = Optional.of("Some Text")
                .map(StringValidator::new)
                .get()
                .validate();
        assertTrue(output);
    }

    @Test
    void validate_Integer() {
        boolean output = Optional.of(10)
                .map(IntegerValidator::new)
                .get()
                .validate();
        assertTrue(output);
    }

    @Test
    void validate_IntegerNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new IntegerValidator(-1).validate());
        assertEquals(String.format("The Value %s cannot be negative", -1), exception.getMessage());
    }
}