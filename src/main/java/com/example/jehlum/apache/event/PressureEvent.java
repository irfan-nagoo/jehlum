package com.example.jehlum.apache.event;

/**
 * @author irfan.nagoo
 */
public class PressureEvent implements CustomEvent {
    private final String pressure;

    public PressureEvent(String pressure) {
        this.pressure = pressure;
    }
}