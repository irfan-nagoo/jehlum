package com.example.jehlum.oops.action;

/**
 * @author irfan.nagoo
 */

public abstract class Action {

    protected final String command;

    protected Action(String command) {
        this.command = command;
    }


    public abstract void execute();

}
