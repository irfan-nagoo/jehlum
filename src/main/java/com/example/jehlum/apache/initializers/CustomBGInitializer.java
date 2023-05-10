package com.example.jehlum.apache.initializers;

import org.apache.commons.lang3.concurrent.CallableBackgroundInitializer;
import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.MultiBackgroundInitializer;

/**
 * @author irfan.nagoo
 */
public class CustomBGInitializer {

    public Integer customInitializer() throws ConcurrentException {
        // custom background initializer
        CustomBackgroundInitializer initializer = new CustomBackgroundInitializer();
        initializer.start();
        return initializer.get();
    }

    public String callableInitializer() throws ConcurrentException {
        // background callable
        CallableBackgroundInitializer<String> cbInitializer = new CallableBackgroundInitializer<>(
                () -> String.format("Running Callable in background Thread[%s]", Thread.currentThread().getName()));
        cbInitializer.start();
        return cbInitializer.get();
    }

    public boolean multiInitializer() throws ConcurrentException {
        // multiple background initializer
        MultiBackgroundInitializer mbInitializer = new MultiBackgroundInitializer();
        mbInitializer.addInitializer("custom", new CustomBackgroundInitializer());
        mbInitializer.addInitializer("callable", new CallableBackgroundInitializer<>(() -> 1000));
        mbInitializer.start();
        MultiBackgroundInitializer.MultiBackgroundInitializerResults results = mbInitializer.get();
        return results.isSuccessful();
    }


}
