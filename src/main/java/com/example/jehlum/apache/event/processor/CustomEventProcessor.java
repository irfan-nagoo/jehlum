package com.example.jehlum.apache.event.processor;

import com.example.jehlum.apache.event.CustomEvent;
import com.example.jehlum.apache.event.PressureEvent;
import com.example.jehlum.apache.event.TemperatureEvent;
import com.example.jehlum.apache.event.listener.CustomListener;
import com.example.jehlum.apache.event.listener.PressureListener;
import com.example.jehlum.apache.event.listener.TemperatureListener;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author irfan.nagoo
 */

public class CustomEventProcessor {

    private final List<CustomListener> listeners;

    public CustomEventProcessor() {
        this(new ArrayList<>());
    }

    public CustomEventProcessor(List<CustomListener> listeners) {
        this.listeners = listeners;
    }

    public boolean dispatchEvent(@Nonnull CustomEvent event) {
        if (event instanceof TemperatureEvent) {
            listeners.stream()
                    .filter(x -> x instanceof TemperatureListener)
                    .forEach(x -> x.process(event));
        } else if (event instanceof PressureEvent) {
            listeners.stream()
                    .filter(x -> x instanceof PressureListener)
                    .forEach(x -> x.process(event));
        } else {
            throw new IllegalArgumentException(String.format("Invalid Event Type [%s]", event.getClass()));
        }
        return true;
    }

    public void registerListener(CustomListener listener) {
        listeners.add(listener);
    }

    public void deregisterListener(CustomListener listener) {
        listeners.remove(listener);
    }


}
