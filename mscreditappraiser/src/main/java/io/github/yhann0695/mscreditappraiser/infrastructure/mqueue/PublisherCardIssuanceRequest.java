package io.github.yhann0695.mscreditappraiser.infrastructure.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.yhann0695.mscreditappraiser.domain.model.CardIssuanceRequestData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static io.github.yhann0695.mscreditappraiser.util.Utils.convertIntoJson;

@Component
@RequiredArgsConstructor
public class PublisherCardIssuanceRequest {
    private final RabbitTemplate rabbitTemplate;
    private final Queue cardIssueQueue;

    public void requestCard(CardIssuanceRequestData data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(cardIssueQueue.getName(), json);
    }
}
