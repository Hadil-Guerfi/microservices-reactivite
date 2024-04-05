package com.example.emprunt.emprunt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmpruntService {

  private  EmpruntRepository empruntRepository;

    public EmpruntService(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    public Flux<Emprunt> findAll() {
    return empruntRepository.findAll();

  }

  public Mono<Emprunt> findById(Long id) {
    return empruntRepository.findById(id);
  }

  public Mono<Emprunt> save(EmpruntRequest request) {
    return empruntRepository.save(
            Emprunt.builder()
                    .id_livre(request.getId_livre())
                    .id_empruteur(request.getId_empruteur())
                    .date_debut(request.getDate_debut())
                    .date_fin(request.getDate_fin())
                    .build()
    );
  }


  public void deleteById(Long id) {
    empruntRepository.deleteById(id).subscribe();
  }
}
