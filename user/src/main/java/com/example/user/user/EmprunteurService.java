package com.example.user.user;

import java.time.Duration;

import com.example.user.user.EmprunteurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmprunteurService {

  private  EmprunteurRepository emprunteurRepository;

    public EmprunteurService(EmprunteurRepository emprunteurRepository) {
        this.emprunteurRepository = emprunteurRepository;
    }

    public Flux<Emprunteur> findAll() {
    return emprunteurRepository.findAll();

  }

  public Mono<Emprunteur> findById(Long id) {
    return emprunteurRepository.findById(id);
  }

  public Mono<Emprunteur> save(EmprunteurRequest request) {
    return emprunteurRepository.save(
            Emprunteur.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .build()
    );
  }

  public Flux<Emprunteur> findByFirstname(String firstname) {
    return emprunteurRepository.findAllByFirstnameContainingIgnoreCase(firstname);
  }

  public void deleteById(Long id) {
    emprunteurRepository.deleteById(id).subscribe();
  }
}
