package com.example.emprunt.kafka;

import com.example.emprunt.emprunt.EmpruntRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import static java.lang.String.format;

@Service
@Slf4j
public class demandeEmpruntConsumer {

    @KafkaListener(topics = "demande_emprunt", groupId = "demandeEmprunt")
    public void consumeJsonMsg(EmpruntRequest demandeEmprunteEvent) {
        log.info(format("Consuming the message from demande_emprunt :: %s", demandeEmprunteEvent.toString()));
    }

}
