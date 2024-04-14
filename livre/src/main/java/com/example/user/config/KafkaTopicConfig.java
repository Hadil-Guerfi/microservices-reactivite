package com.example.user.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class KafkaTopicConfig {

//    @Value("${spring.kafka.properties.topic.name}")
//    private String topicName;

    @Bean
    public NewTopic topicDemandeEprunt() {
        return TopicBuilder
                .name("demande_emprunt")
                .build();
    }
}

