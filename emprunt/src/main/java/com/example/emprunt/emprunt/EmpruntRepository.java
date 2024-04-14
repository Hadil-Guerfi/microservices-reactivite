package com.example.emprunt.emprunt;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface EmpruntRepository extends ReactiveCrudRepository<Emprunt, Long> {
    @Query("SELECT * FROM emprunt WHERE date_fin < :currentDate")
    Flux<Emprunt> findEmpruntWithDateRetourExpire(@Param("currentDate") LocalDate currentDate);
}