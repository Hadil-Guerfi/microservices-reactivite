package com.example.user.kafka;

import com.example.emprunt.emprunt.EmpruntRequest;
import com.example.user.livre.LivreRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import com.example.emprunt.emprunt.EmpruntRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@Slf4j
public class VerifyIdLivreConsumer {



    private final LivreRepository livreRepository;


        private final KafkaTemplate<String, String> kafkaTemplate;

        public VerifyIdLivreConsumer(LivreRepository livreRepository, KafkaTemplate<String, String> kafkaTemplate) {
            this.livreRepository = livreRepository;
            this.kafkaTemplate = kafkaTemplate;
        }

        @KafkaListener(topics = "verifyIdLivre", groupId = "livreConsumer")
        public void consumeJsonMsg(EmpruntRequest demandeEmprunteEvent) {
            log.info("Consuming the message from demande_emprunt topic: {}", demandeEmprunteEvent);

            Long idLivre = demandeEmprunteEvent.getId_livre();

            // Check if livre ID exists
            Mono<Boolean> livreExistsMono = livreRepository.existsById(idLivre);

            // Send verification result
            livreExistsMono.flatMap(livreExists ->
                    sendVerificationResult(livreExists)
            ).subscribe(
                    unused -> log.info("Message sent to verifyIdLivre Topic: {}", idLivre),
                    error -> log.error("Failed to send message to verifyIdLivre Topic: {}", idLivre, error)
            );
        }

    private Mono<Void> sendVerificationResult(boolean livreExists) {
        return Mono.fromRunnable(() -> {
            log.info("Sending response to resultat_verification Topic: {}", String.valueOf(livreExists));
            kafkaTemplate.send("resultat_verification", String.valueOf(livreExists));
        });
    }


}
