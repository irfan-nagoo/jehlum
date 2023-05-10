package com.example.jehlum.apache.event.processor;

import com.example.jehlum.apache.event.PressureEvent;
import com.example.jehlum.apache.event.TemperatureEvent;
import com.example.jehlum.apache.event.listener.CustomListener;
import com.example.jehlum.apache.event.listener.PressureListener;
import com.example.jehlum.apache.event.listener.TemperatureListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CustomEventProcessorTest {

    private CustomEventProcessor eventProcessor;

    @Test
    void dispatchEvent() {
        eventProcessor = new CustomEventProcessor();
        CustomListener pressureListener = new PressureListener();
        CustomListener temperatureListener = new TemperatureListener();
        // register listeners
        eventProcessor.registerListener(pressureListener);
        eventProcessor.registerListener(temperatureListener);

        // trigger events
        assertTrue(eventProcessor.dispatchEvent(new TemperatureEvent("32 C")));
        assertTrue(eventProcessor.dispatchEvent(new PressureEvent("99 kg/cm2")));

        // deregister one of listener
        eventProcessor.deregisterListener(pressureListener);
        assertDoesNotThrow(() -> eventProcessor.dispatchEvent(new TemperatureEvent("35 C")));
        assertTrue(eventProcessor.dispatchEvent(new PressureEvent("97 kg/cm2")));
    }


}