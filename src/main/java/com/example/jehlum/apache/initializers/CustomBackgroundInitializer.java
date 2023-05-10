package com.example.jehlum.apache.initializers;

import org.apache.commons.lang3.concurrent.BackgroundInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irfan.nagoo
 */
public class CustomBackgroundInitializer extends BackgroundInitializer<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBackgroundInitializer.class);

    @Override
    protected Integer initialize() {
        LOGGER.info("Initialize something in background Thread [{}]", Thread.currentThread().getName());
        return 1;
    }
}