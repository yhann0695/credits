package io.github.yhann0695.mscreditappraiser.application.exception;

public class CustomerDataNotFoundException extends Exception {
    public CustomerDataNotFoundException() {
        super("Customer data not found for the informed CPF");
    }
}
