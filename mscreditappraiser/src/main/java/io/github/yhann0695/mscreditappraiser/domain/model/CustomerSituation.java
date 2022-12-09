package io.github.yhann0695.mscreditappraiser.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSituation {
    private CustomerData customerData;
    private List<CustomerCard> cards;
}
