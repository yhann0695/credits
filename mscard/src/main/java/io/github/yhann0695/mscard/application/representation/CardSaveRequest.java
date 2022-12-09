package io.github.yhann0695.mscard.application.representation;

import io.github.yhann0695.mscard.domain.Card;
import io.github.yhann0695.mscard.domain.CardFlag;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class CardSaveRequest {
    private String name;
    private String flag;
    private BigDecimal income;
    private BigDecimal basicLimit;


    public static Card of(CardSaveRequest request) {
        return new Card(
                request.getName(), CardFlag.valueOf(request.getFlag()), request.getIncome(), request.getBasicLimit()
        );
    }
}
