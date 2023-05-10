package com.example.jehlum.apache.concurrency;

import org.apache.commons.lang3.concurrent.CircuitBreaker;
import org.apache.commons.lang3.concurrent.EventCountCircuitBreaker;
import org.apache.commons.lang3.concurrent.ThresholdCircuitBreaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CircuitBreakFinderTest {

    private CircuitBreakFinder<?> circuitBreakFinder;

    @Test
    void findCircuitStatus_EventCount() {
        CircuitBreaker<Integer> circuitBreaker = new EventCountCircuitBreaker(1, 10, TimeUnit.MILLISECONDS,
                1, 10, TimeUnit.MILLISECONDS);
        circuitBreakFinder = new CircuitBreakFinder<>(circuitBreaker, 1);
        assertEquals(CircuitBreakFinder.State.CLOSED, circuitBreakFinder.findCircuitStatus());
        assertEquals(CircuitBreakFinder.State.OPEN, circuitBreakFinder.findCircuitStatus());
    }

    @Test
    void findCircuitStatus_Threshold() {
        CircuitBreaker<Long> circuitBreaker = new ThresholdCircuitBreaker(1L);
        circuitBreakFinder = new CircuitBreakFinder<>(circuitBreaker, 1L);
        assertEquals(CircuitBreakFinder.State.OPEN, circuitBreakFinder.findCircuitStatus());
        assertEquals(CircuitBreakFinder.State.CLOSED, circuitBreakFinder.findCircuitStatus());
    }

}