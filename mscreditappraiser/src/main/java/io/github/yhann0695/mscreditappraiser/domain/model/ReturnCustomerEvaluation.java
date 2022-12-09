package io.github.yhann0695.mscreditappraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnCustomerEvaluation {
    private List<ApprovedCard> cards;
}
