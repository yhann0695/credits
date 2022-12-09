package io.github.yhann0695.mscard.application.representation;

import io.github.yhann0695.mscard.domain.CustomerCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsByCustomersResponse {
    private String name;
    private String flag;
    private BigDecimal limitReleased;

    public CardsByCustomersResponse(CustomerCard entity) {
        this.name = entity.getCard().getName();
        this.flag = entity.getCard().getFlag().toString();
        this.limitReleased = entity.getLimitReleased();
    }

    public static List<CardsByCustomersResponse> of(List<CustomerCard> customerCards) {
        return customerCards.stream().map(CardsByCustomersResponse::new).collect(Collectors.toList());
    }
}
