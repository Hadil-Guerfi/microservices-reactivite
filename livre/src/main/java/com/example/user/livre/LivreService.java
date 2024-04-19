package com.example.user.livre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LivreService {

  private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public Flux<Livre> findAll() {
    return livreRepository.findAll();

  }


  public Mono<Livre> findById(Long id) {
    return livreRepository.findById(id);
  }


  public Mono<Integer> getNumberOfExemplaires(Long id) {
    // Implement logic to fetch the number of exemplaires for the given book ID
    return livreRepository.findById(id)
            .map(Livre::getNbr_exemplaire)
            .switchIfEmpty(Mono.just(0)); // Return 0 if no Livre found with the given ID
  }

//
//  public Mono<Livre> save(LivreRequest request) {
//    return livreRepository.save(
//        Livre.builder()
//                .nom_auteur(request.getNom_auteur())
//            .titre_livre(request.getTitre_livre())
//            .nbr_exemplaire(request.getNbr_exemplaire())
//            .build()
//    );
//  }

    public Mono<Void> decreaseNbrExemplaire(Long id) {
        return livreRepository.findById(id)
                .flatMap(livre -> {
                    int updatedNbrExemplaire = livre.getNbr_exemplaire() - 1;
                    livre.setNbr_exemplaire(updatedNbrExemplaire);
                    return livreRepository.save(livre)
                            .then(Mono.fromRunnable(() -> System.out.println(" \n ------------------------------------------------------------------------------------------------- 'nbr_exemplaire' decreased successfully !  -----------------------------------------------------")));
                });
    }


  public void deleteById(Long id) {
    livreRepository.deleteById(id).subscribe();
  }
}
