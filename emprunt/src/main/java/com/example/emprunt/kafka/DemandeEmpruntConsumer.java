package com.example.emprunt.kafka;

import com.example.emprunt.emprunt.Emprunt;
import com.example.emprunt.emprunt.EmpruntRepository;
import com.example.emprunt.emprunt.EmpruntRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Data
public class DemandeEmpruntConsumer {
    private final EmpruntRepository empruntRepository;

    @KafkaListener(topics = "demande_emprunt", groupId = "demandeEmprunt")
    public void consumeJsonMsg(EmpruntRequest demandeEmprunteEvent) {
        log.info("Consuming the message from demande_emprunt: {}", demandeEmprunteEvent);

        Emprunt emprunt = Emprunt.builder()
                .id_livre(demandeEmprunteEvent.getId_livre())
                .id_emprunteur(demandeEmprunteEvent.getId_empruteur())
                .date_debut(demandeEmprunteEvent.getDate_debut())
                .date_fin(demandeEmprunteEvent.getDate_fin())
                .build();
                empruntRepository.save(emprunt)
                .subscribe(savedEmprunt -> {
                    log.info("Emprunt saved in the database: {}", savedEmprunt);
                }, error -> {
                    log.error("Error occurred while saving Emprunt: {}", emprunt, error);
                });
    }
}
