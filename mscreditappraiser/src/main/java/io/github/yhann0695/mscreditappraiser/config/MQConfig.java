package io.github.yhann0695.mscreditappraiser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String cardIssueQueue;

    @Bean
    public Queue cardIssueQueue() {
        return new Queue(cardIssueQueue, true);
    }
}
