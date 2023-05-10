package com.example.jehlum.apache.concurrency;

import org.apache.commons.lang3.concurrent.Computable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

/**
 * @author irfan.nagoo
 */

public class IsPrimeEvaluator implements Computable<Long, Boolean> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsPrimeEvaluator.class);

    @Override
    public Boolean compute(@Nonnull Long arg) {
        LOGGER.info("Computing for input [{}]", arg);
        for (long i = Math.round(Math.sqrt(arg)); i > 1; i--) {
            if (arg % i == 0) {
                return false;
            }
        }
        return true;
    }
}
