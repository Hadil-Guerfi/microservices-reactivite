package com.example.user.kafka;

import com.example.emprunt.emprunt.EmpruntRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class demandeEmpruntProducer {

//    private final NewTopic topicDemandeEprunt; // Using constructor injection
//
//    private final KafkaTemplate<String, EmpruntRequest> kafkaTemplate;
//
//    public void sendMessage(EmpruntRequest event) {
//        log.info(format("Sending Demande Emprunt Event :: %s", event));
//
//
//            Message<EmpruntRequest> message = MessageBuilder
//                    .withPayload(event)
//                    .setHeader(KafkaHeaders.TOPIC, topicDemandeEprunt.name())
//                    .build();
//
//            kafkaTemplate.send(message);
//
//    }
}