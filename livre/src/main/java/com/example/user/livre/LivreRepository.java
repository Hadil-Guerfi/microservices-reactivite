package com.example.user.livre;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends ReactiveCrudRepository<Livre, Long> {
}
