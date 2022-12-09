package io.github.yhann0695.mscard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CustomerCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_card")
    private Card card;
    private BigDecimal limitReleased;

    public CustomerCard(String cpf, Card card, BigDecimal limitReleased) {
        this.cpf = cpf;
        this.card = card;
        this.limitReleased = limitReleased;
    }
}
