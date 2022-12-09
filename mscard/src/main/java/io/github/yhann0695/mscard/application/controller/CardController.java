package io.github.yhann0695.mscard.application.controller;

import io.github.yhann0695.mscard.application.representation.CardSaveRequest;
import io.github.yhann0695.mscard.application.representation.CardsByCustomersResponse;
import io.github.yhann0695.mscard.application.service.CardService;
import io.github.yhann0695.mscard.application.service.CustomerCardService;
import io.github.yhann0695.mscard.domain.Card;
import io.github.yhann0695.mscard.domain.CustomerCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    private final CustomerCardService customerCardService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody CardSaveRequest request) {
        Card card = CardSaveRequest.of(request);
        cardService.save(card);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("income={income}")
                .buildAndExpand(card.getIncome())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getLessOrEqualIncome(@RequestParam("income") Long income) {
        List<Card> lessOrEqualIncome = cardService.getLessOrEqualIncome(income);

        if (lessOrEqualIncome.isEmpty())
            ResponseEntity.notFound().build();

        return ResponseEntity.ok(lessOrEqualIncome);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsByCustomersResponse>> getCardsByCustomers(@RequestParam("cpf") String cpf) {
        List<CustomerCard> customerCards = customerCardService.searchCardsByCpf(cpf);
        return ResponseEntity.ok(CardsByCustomersResponse.of(customerCards));
    }
}
