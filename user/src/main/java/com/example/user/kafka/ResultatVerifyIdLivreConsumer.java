package com.example.user.kafka;

import com.example.emprunt.emprunt.EmpruntRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class ResultatVerifyIdLivreConsumer {
    private  String lastMessage ;
    private EmpruntRequest demandeEmprunt;
    private final demandeEmpruntProducer demandeEmpruntProd;

    @KafkaListener(topics = "resultat_verification", groupId = "userConsumer")
    public synchronized String consumeJMsg(String msg) {
        log.info("Consuming the message from resultat_verification topic: {}", msg);
//        System.out.println("Demande Emprunnnnnnnnnnnnnnnnnnntttttttttttt"+demandeEmprunt);
        Boolean reponse=Boolean.parseBoolean(msg);
//        System.out.println("responseeeeeeeeeeeeeeeeeeeee"+reponse);

        if(reponse){
//            System.out.println("Sending demande d'emprunt to demande_emprunt  "+demandeEmprunt);
            demandeEmpruntProd.sendMessage(demandeEmprunt);
        }else{
            System.out.println(" \n ------------------------------------------------------------------------------------------------- Livre Id doen't exist -----------------------------------------------------------------------------------------------------------------------");
        }

        return msg;
    }

}

