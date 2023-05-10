package com.example.jehlum.apache.event.listener;

import com.example.jehlum.apache.event.CustomEvent;

/**
 * @author irfan.nagoo
 */
@FunctionalInterface
public interface CustomListener {

    void process(CustomEvent event);
}
