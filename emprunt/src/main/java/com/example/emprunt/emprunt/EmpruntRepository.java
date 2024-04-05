package com.example.emprunt.emprunt;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmpruntRepository extends ReactiveCrudRepository<Emprunt, Long> {

}
