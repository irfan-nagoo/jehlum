package com.example.jehlum.apache.concurrency;

import org.apache.commons.lang3.concurrent.Computable;
import org.apache.commons.lang3.concurrent.Memoizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

class IsPrimeEvaluatorTest {

    private Computable<Long,Boolean> computable;

    @Test
    void compute() throws InterruptedException {
        computable = spy(new IsPrimeEvaluator());
        Memoizer<Long, Boolean> memoizer = new Memoizer<>(computable);
        memoizer.compute(23L);
        verify(computable, times(1)).compute(23L);

        // cached result
        memoizer.compute(23L);
        verify(computable, times(1)).compute(23L);
        assertEquals(true, memoizer.compute(7L));
        verify(computable, times(2)).compute(anyLong());
    }
}