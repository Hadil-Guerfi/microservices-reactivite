package com.example.user.kafka.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {
    @KafkaListener(topics = "notificationTopic", groupId = "userConsumer")
    public void consumeMsg(String msg) {
        log.info(String.format("Consuming message from notificationTopic:: %s", msg));
    }
}
