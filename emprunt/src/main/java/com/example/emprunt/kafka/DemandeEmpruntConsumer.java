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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Data
public class DemandeEmpruntConsumer {
   /* private final EmpruntRepository empruntRepository;

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
    */

    private final EmpruntRepository empruntRepository;
    private final WebClient webClient;

    public DemandeEmpruntConsumer(EmpruntRepository empruntRepository, WebClient.Builder webClientBuilder) {
        this.empruntRepository = empruntRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8222/api/v1/livres/").build();
    }

    @KafkaListener(topics = "demande_emprunt", groupId = "demandeEmprunt")
    public void consumeJsonMsg(EmpruntRequest demandeEmprunteEvent) {
        log.info("Consuming the message from demande_emprunt: {}", demandeEmprunteEvent);

        // Make a request to livre microservice to check the number of exemplars
        webClient.get()
                .uri("/{id}/nbr_exemplaire", demandeEmprunteEvent.getId_livre())
                .retrieve()
                .bodyToMono(Integer.class)
                .flatMap(nbrExemplaire -> {
                    if (nbrExemplaire > 0) {
                        Emprunt emprunt = Emprunt.builder()
                                .id_livre(demandeEmprunteEvent.getId_livre())
                                .id_emprunteur(demandeEmprunteEvent.getId_empruteur())
                                .date_debut(demandeEmprunteEvent.getDate_debut())
                                .date_fin(demandeEmprunteEvent.getDate_fin())
                                .build();
                                   return empruntRepository.save(emprunt)
                                .flatMap(savedEmprunt -> {
                                    log.info("'nbr_exemplaire' sufficient and Emprunt saved in the database: {}", savedEmprunt);
                                    // After saving the emprunt, decrease nbr_exemplaire
                                    return webClient.put()
                                            .uri("/{id}/decreaseLivre", demandeEmprunteEvent.getId_livre())
                                            .retrieve()
                                            .toBodilessEntity()
                                            .then(Mono.just(savedEmprunt));
                                })
                                .doOnError(error -> log.error("Error occurred while saving Emprunt: {}", emprunt, error));
                    } else {
                        log.warn("'nbr_exemplaire' insufficient.");
                        return Mono.empty();
                    }
                })
                .subscribe(); // Subscribe to trigger the reactive chain






}





}
