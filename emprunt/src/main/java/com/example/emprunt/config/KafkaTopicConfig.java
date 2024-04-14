package com.example.emprunt.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topicVerifyIdLivre() {
        return TopicBuilder
                .name("verifyIdLivre")
                .build();
    }


    @Bean
    public NewTopic notificationTopic() {
        return TopicBuilder
                .name("notificationTopic")
                .build();
    }

}

