package com.bot.gobot.logicJohan;

public class AvoidStackOverflowException extends RuntimeException {
    public AvoidStackOverflowException(String foundIt) {
        super(foundIt);
    }
}
