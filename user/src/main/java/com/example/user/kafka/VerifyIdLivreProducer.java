package com.example.user.kafka;

import com.example.emprunt.emprunt.EmpruntRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class VerifyIdLivreProducer {

    private final KafkaTemplate<String, EmpruntRequest> kafkaTemplate;

    public VerifyIdLivreProducer(KafkaTemplate<String, EmpruntRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(EmpruntRequest event) {
        log.info(format("Sending demande d'emprunt to VerifyIdLivre topic :: %s", event));


        Message<EmpruntRequest> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "verifyIdLivre")
                .build();

        kafkaTemplate.send(message);

    }
}
