package dev.dluks.brasileirao.exceptions;

public class InvalidCardExpception extends RuntimeException {
    public InvalidCardExpception(String message) {
        super(message);
    }
}
