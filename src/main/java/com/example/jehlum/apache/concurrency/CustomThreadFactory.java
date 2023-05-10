package com.example.jehlum.apache.concurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author irfan.nagoo
 */

public class CustomThreadFactory implements ThreadFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomThreadFactory.class);

    private final AtomicLong threadCounter;
    private final String namePattern;
    private final int priority;
    private final boolean isDaemon;
    private final ThreadGroup threadGroup;

    public CustomThreadFactory(Builder builder) {
        this.namePattern = builder.namePattern;
        this.priority = builder.priority;
        this.isDaemon = builder.isDaemon;
        this.threadGroup = Optional.of(builder.threadGroup).orElse(Thread.currentThread().getThreadGroup());
        this.threadCounter = new AtomicLong();
    }

    @Override
    public Thread newThread(@Nonnull Runnable r) {
        String name = String.format(namePattern, threadCounter.incrementAndGet());
        LOGGER.info("Creating new Thread[{}]", name);
        Thread thread = new Thread(threadGroup, r);
        thread.setName(name);
        thread.setPriority(priority);
        thread.setDaemon(isDaemon);
        return thread;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String namePattern;
        private int priority;
        private boolean isDaemon;
        private ThreadGroup threadGroup;

        public Builder setNamePattern(String namePattern) {
            this.namePattern = namePattern + "-%d";
            return this;
        }

        public Builder setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder setDaemon(boolean daemon) {
            isDaemon = daemon;
            return this;
        }

        public Builder setThreadGroup(ThreadGroup threadGroup) {
            this.threadGroup = threadGroup;
            return this;
        }

        public CustomThreadFactory build() {
            return new CustomThreadFactory(this);
        }
    }

}
