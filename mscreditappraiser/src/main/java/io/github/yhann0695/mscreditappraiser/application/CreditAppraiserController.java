package io.github.yhann0695.mscreditappraiser.application;

import io.github.yhann0695.mscreditappraiser.application.exception.CardRequestErrorException;
import io.github.yhann0695.mscreditappraiser.application.exception.CustomerDataNotFoundException;
import io.github.yhann0695.mscreditappraiser.application.exception.MicroserviceCommunicationError;
import io.github.yhann0695.mscreditappraiser.application.service.CreditAppraiserService;
import io.github.yhann0695.mscreditappraiser.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/credit-appraiser")
@RequiredArgsConstructor
public class CreditAppraiserController {
    private final CreditAppraiserService creditAppraiserService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "customer-situation", params = "cpf")
    public ResponseEntity consultCustomerSituation(
            @RequestParam("cpf") String cpf)
    {
        try {
            CustomerSituation situation = creditAppraiserService.getCustomerSituation(cpf);
            return ResponseEntity.ok(situation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationError e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity carryOutEvaluation(@RequestBody EvaluationData data) {
        try {
            ReturnCustomerEvaluation returnCustomerEvaluation = creditAppraiserService.
                    carryOutEvaluation(data.getCpf(), data.getIncome());
            return ResponseEntity.ok(returnCustomerEvaluation);
        } catch (CustomerDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (MicroserviceCommunicationError e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("/request-card")
    public ResponseEntity requestCard(@RequestBody CardIssuanceRequestData data) {
        try {
            CardRequestProtocol protocol = creditAppraiserService.cardIssueRequest(data);
            return ResponseEntity.ok(protocol);
        } catch (CardRequestErrorException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
