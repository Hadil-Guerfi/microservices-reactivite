package com.example.emprunt.kafka.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class Producer {

        private final KafkaTemplate<String, String> kafkaTemplate;
        public void sendMessage(String msg) {
            log.info(String.format("Sending message to notification Topic:: %s",msg));
            kafkaTemplate.send("notificationTopic", msg);
        }

}
