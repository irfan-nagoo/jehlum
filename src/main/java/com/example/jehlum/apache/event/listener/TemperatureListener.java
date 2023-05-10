package com.example.jehlum.apache.event.listener;

import com.example.jehlum.apache.event.CustomEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author irfan.nagoo
 */
public class TemperatureListener implements CustomListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureListener.class);

    @Override
    public void process(CustomEvent event) {
        LOGGER.info("The Current Temperature is [{}]", event.getEventValue());
    }
}