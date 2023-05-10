package com.example.jehlum.guava.validator;

import com.google.common.base.Preconditions;

/**
 * @author irfan.nagoo
 */
public class IntegerValidator extends ObjectValidator<Integer> {

    public IntegerValidator(Integer value) {
        super(value);
    }

    @Override
    public boolean validate() {
        super.validate();
        Preconditions.checkArgument(value >= 0, "The Value %s cannot be negative", value);
        return true;
    }
}
