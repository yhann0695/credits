package io.github.yhann0695.mscreditappraiser.infrastructure.clients;

import io.github.yhann0695.mscreditappraiser.domain.model.Card;
import io.github.yhann0695.mscreditappraiser.domain.model.CustomerCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscard", path = "/cards")
public interface CardControllerClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CustomerCard>> getCardsByCustomers(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<Card>> getLessOrEqualIncome(@RequestParam("income") Long income);
}
