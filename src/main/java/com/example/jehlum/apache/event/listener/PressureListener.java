package com.example.jehlum.apache.event.listener;

import com.example.jehlum.apache.event.CustomEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irfan.nagoo
 */
public class PressureListener implements CustomListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PressureListener.class);

    @Override
    public void process(CustomEvent event) {
        LOGGER.info("The Current Pressure is [{}]", event.getEventValue());
    }
}