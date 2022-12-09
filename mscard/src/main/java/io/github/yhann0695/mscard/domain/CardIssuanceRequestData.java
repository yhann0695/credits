package io.github.yhann0695.mscard.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardIssuanceRequestData {
    private Long cardId;
    private String cpf;
    private String address;
    private BigDecimal limitReleased;
}