package com.example.jehlum.oops.constants;

/**
 * @author irfan.nagoo
 */
public enum ActionType {
    ADD, MODIFY, DELETE, SUMMARY;

    public static ActionType getAction(String input) {
        return ActionType.valueOf(input.split("[\\s+]")[0]);
    }
}
