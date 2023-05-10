package com.example.jehlum.apache.event;

/**
 * @author irfan.nagoo
 */
public interface CustomEvent {
    default String getEventValue() {
        return this.toString();
    }
}
