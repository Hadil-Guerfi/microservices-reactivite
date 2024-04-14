package com.example.user.livre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class LivreService {

  private  LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public Flux<Livre> findAll() {
    return livreRepository.findAll();

  }

  public Mono<Livre> findById(Long id) {
    return livreRepository.findById(id);
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


  public void deleteById(Long id) {
    livreRepository.deleteById(id).subscribe();
  }
}
