package com.example.jehlum.rxjava.retriever;

/**
 * @author irfan.nagoo
 */
public interface ContentRetriever<I, O> {

    O retrieve(I input);
}
