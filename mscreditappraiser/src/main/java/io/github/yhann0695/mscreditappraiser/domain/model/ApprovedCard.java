package io.github.yhann0695.mscreditappraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedCard {
    private String card;
    private String flag;
    private BigDecimal approvedLimit;
}
