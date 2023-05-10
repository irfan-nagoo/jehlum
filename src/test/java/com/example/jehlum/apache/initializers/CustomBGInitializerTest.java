package com.example.jehlum.apache.initializers;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CustomBGInitializerTest {

    private CustomBGInitializer initializer;

    @BeforeEach
    void setup() {
        initializer = new CustomBGInitializer();
    }

    @Test
    void customInitializer() throws ConcurrentException {
        assertEquals(1, initializer.customInitializer());
    }

    @Test
    void callableInitializer() throws ConcurrentException {
        assertTrue(initializer.callableInitializer().contains("Running Callable in background Thread"));
    }

    @Test
    void multiInitializer() throws ConcurrentException {
        assertTrue(initializer.multiInitializer());
    }

}