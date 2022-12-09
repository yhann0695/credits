package io.github.yhann0695.mscreditappraiser.application.exception;

public class CardRequestErrorException extends RuntimeException {
    public CardRequestErrorException(String message) {
        super(message);
    }
}
