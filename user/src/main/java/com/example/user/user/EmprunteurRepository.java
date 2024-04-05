package com.example.user.user;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmprunteurRepository extends ReactiveCrudRepository<Emprunteur, Long> {
  Flux<Emprunteur> findAllByFirstnameContainingIgnoreCase(String firstname);

}
