package com.example.jehlum.apache.concurrency;

import org.apache.commons.lang3.concurrent.CircuitBreaker;

/**
 * @author irfan.nagoo
 */
public class CircuitBreakFinder<T> {

    enum State {
        OPEN, CLOSED
    }

    private T counter;
    private final CircuitBreaker<T> circuitBreaker;

    public CircuitBreakFinder(CircuitBreaker<T> circuitBreaker, T counter) {
        this.circuitBreaker = circuitBreaker;
        this.counter = counter;
    }

    public State findCircuitStatus() {
        if (circuitBreaker.incrementAndCheckState(counter)) {
            return State.CLOSED;
        } else {
            return State.OPEN;
        }
    }

}
