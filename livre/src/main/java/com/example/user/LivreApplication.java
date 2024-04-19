package com.example.user;

import com.example.user.livre.Livre;
import com.example.user.livre.LivreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication()
public class LivreApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivreApplication.class, args);
    }
/*

    @Bean
    public CommandLineRunner runner(LivreRepository livreRepository) {
        return args -> {
            for (int i = 1; i < 21; i++) {
                livreRepository.save(
                        Livre.builder()
                                .titre_livre("livre" + i)
                                .nom_auteur("auteur" + i)
                                .nbr_exemplaire(i)
                                .build()
                ).subscribe();
            }
        };
    }

*/
}
