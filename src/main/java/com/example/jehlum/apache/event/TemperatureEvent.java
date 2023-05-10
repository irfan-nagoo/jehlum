package com.example.jehlum.apache.event;

/**
 * @author irfan.nagoo
 */
public class TemperatureEvent implements CustomEvent {
    private final String temperature;

    public TemperatureEvent(String temperature) {
        this.temperature = temperature;
    }
}
