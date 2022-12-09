package io.github.yhann0695.mscreditappraiser.application.service;

import feign.FeignException;
import io.github.yhann0695.mscreditappraiser.application.exception.CardRequestErrorException;
import io.github.yhann0695.mscreditappraiser.application.exception.CustomerDataNotFoundException;
import io.github.yhann0695.mscreditappraiser.application.exception.MicroserviceCommunicationError;
import io.github.yhann0695.mscreditappraiser.domain.model.*;
import io.github.yhann0695.mscreditappraiser.infrastructure.clients.CardControllerClient;
import io.github.yhann0695.mscreditappraiser.infrastructure.clients.ClientControllerClient;
import io.github.yhann0695.mscreditappraiser.infrastructure.mqueue.PublisherCardIssuanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {
    private final ClientControllerClient clientControllerClient;
    private final CardControllerClient cardControllerClient;
    private final PublisherCardIssuanceRequest publisherCardIssuanceRequest;

    public CustomerSituation getCustomerSituation(String cpf)
            throws CustomerDataNotFoundException, MicroserviceCommunicationError
    {
        try {
            ResponseEntity<CustomerData> customerData = clientControllerClient.detailsClient(cpf);
            ResponseEntity<List<CustomerCard>> customerCard = cardControllerClient.getCardsByCustomers(cpf);
            return CustomerSituation.builder()
                    .customerData(customerData.getBody())
                    .cards(customerCard.getBody())
                    .build();
        } catch (FeignException.FeignClientException ex) {
            if (HttpStatus.NOT_FOUND.value() == ex.status())
                throw new CustomerDataNotFoundException();
            throw new MicroserviceCommunicationError(ex.getMessage(), ex.status());
        }
    }

    public ReturnCustomerEvaluation carryOutEvaluation(String cpf, Long income)
            throws CustomerDataNotFoundException, MicroserviceCommunicationError
    {
        try {
            ResponseEntity<CustomerData> customerData = clientControllerClient.detailsClient(cpf);
            ResponseEntity<List<Card>> lessOrEqualIncome = cardControllerClient.getLessOrEqualIncome(income);
            List<Card> cards = lessOrEqualIncome.getBody();

            List<ApprovedCard> approvedCardList = cards.stream().map(card -> {
                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageDB = BigDecimal.valueOf(Objects.requireNonNull(customerData.getBody()).getAge());
                var factor = ageDB.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(basicLimit);

                return new ApprovedCard(card.getName(), card.getFlag(), approvedLimit);
            }).collect(Collectors.toList());

            return new ReturnCustomerEvaluation(approvedCardList);

        } catch (FeignException.FeignClientException ex) {
            if (HttpStatus.NOT_FOUND.value() == ex.status())
                throw new CustomerDataNotFoundException();
            throw new MicroserviceCommunicationError(ex.getMessage(), ex.status());
        }
    }

    public CardRequestProtocol cardIssueRequest(CardIssuanceRequestData data) {
        try {
            publisherCardIssuanceRequest.requestCard(data);
            var protocol = UUID.randomUUID().toString();
            return new CardRequestProtocol(protocol);
        } catch (Exception e) {
            throw new CardRequestErrorException(e.getMessage());
        }
    }
}
