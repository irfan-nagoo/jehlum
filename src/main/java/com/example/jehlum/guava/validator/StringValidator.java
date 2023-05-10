package com.example.jehlum.guava.validator;

import com.example.jehlum.guava.validator.ObjectValidator;
import com.google.common.base.Preconditions;

/**
 * @author irfan.nagoo
 */
public class StringValidator extends ObjectValidator<String> {

    public StringValidator(String value) {
        super(value);
    }

    @Override
    public boolean validate() {
        super.validate();
        Preconditions.checkArgument(!value.isEmpty(), "The Value cannot be Empty");
        return true;
    }
}
