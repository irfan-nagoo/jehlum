package com.example.jehlum.guava.list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LoggingListTest {

    private LoggingList<String> loggingList;

    @Test
    void add() {
        // should also log
        loggingList = new LoggingList<>(new ArrayList<>());
        loggingList.add("Hi");
        // should also log
        assertEquals("Hi", loggingList.get(0));
    }

    @Test
    void get() {
        // should also log
        loggingList = new LoggingList<>(new LinkedList<>());
        loggingList.add("Hello");
        assertEquals("Hello", loggingList.get(0));
    }
}