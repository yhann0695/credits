package io.github.yhann0695.mscreditappraiser.application.exception;

import lombok.Getter;

public class MicroserviceCommunicationError extends Exception{
    @Getter
    private Integer status;

    public MicroserviceCommunicationError(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
