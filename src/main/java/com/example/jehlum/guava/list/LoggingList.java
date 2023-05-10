package com.example.jehlum.guava.list;

import com.google.common.collect.ForwardingList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author irfan.nagoo
 */
public class LoggingList<E> extends ForwardingList<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingList.class);

    private final List<E> delegate;

    public LoggingList(List<E> delegate) {
        this.delegate = delegate;
    }

    @Override
    @NonNull
    protected List<E> delegate() {
        return delegate;
    }

    @Override
    public boolean add(E e) {
        LOGGER.info("add element {}", e);
        return super.add(e);
    }

    @Override
    public E get(int i) {
        LOGGER.info("get element at position {}", i);
        return super.get(i);
    }

}