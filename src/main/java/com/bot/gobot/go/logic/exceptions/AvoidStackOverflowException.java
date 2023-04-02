package com.bot.gobot.go.logic.exceptions;

public class AvoidStackOverflowException extends RuntimeException {
    public AvoidStackOverflowException(String foundIt) {
        super(foundIt);
    }
}
