package com.example.jehlum.annotation.finder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ClassFinderTest {

    private ClassFinder classFinder;

    @Test
    void find() {
        List<Class<?>> classList1 = new ClassFinder("com.example.jehlum.guava").find();
        assertEquals(7, classList1.size());

        List<Class<?>> classList2 = new ClassFinder("com.example.jehlum.apache").find();
        assertEquals(7, classList2.size());
    }

    @Test
    void find_Exception() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new ClassFinder("com.example.jehlum.gua").find());
        assertEquals("Package name is invalid", exception.getMessage());
    }

}