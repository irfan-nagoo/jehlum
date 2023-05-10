package com.example.jehlum.guava.validator;

import java.util.Objects;

/**
 * @author irfan.nagoo
 */
public class ObjectValidator<T> {

    protected final T value;

    public ObjectValidator(T value) {
        this.value = value;
    }


    public boolean validate() {
        Objects.requireNonNull(value);
        return true;
    }

}
