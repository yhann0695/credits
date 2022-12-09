package io.github.yhann0695.mscard.infrastructure.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.yhann0695.mscard.domain.Card;
import io.github.yhann0695.mscard.domain.CardIssuanceRequestData;
import io.github.yhann0695.mscard.domain.CustomerCard;
import io.github.yhann0695.mscard.infrastructure.repository.CardRepository;
import io.github.yhann0695.mscard.infrastructure.repository.CustomerCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IssuanceOfCardsSubscribe {
    private final CardRepository cardRepository;
    private final CustomerCardRepository customerCardRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receiveIssueRequest(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            CardIssuanceRequestData data = mapper.readValue(payload, CardIssuanceRequestData.class);
            Card card = cardRepository.findById(data.getCardId()).orElseThrow();
            CustomerCard customerCard = new CustomerCard(data.getCpf(), card, data.getLimitReleased());
            customerCardRepository.save(customerCard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
