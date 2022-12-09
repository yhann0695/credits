package io.github.yhann0695.mscard.application.service;

import io.github.yhann0695.mscard.domain.Card;
import io.github.yhann0695.mscard.infrastructure.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    @Transactional
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> getLessOrEqualIncome(Long value) {
        var income = BigDecimal.valueOf(value);
        return cardRepository.findByIncomeLessThanEqual(income);
    }
}
