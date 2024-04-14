package com.example.livre.livre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;


@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class LivreApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivreApplication.class, args);
    }


    /*
    @Bean
    public CommandLineRunner runner( LivreRepository livreRepository) {
        return args -> {
            for (int i = 1; i < 21; i++) {
                livreRepository.save(
                        Livre.builder()
                                .titre_livre("Test" + i)
                                .nom_auteur("test" + i)
                                .nbr_exemplaire(i)
                                .build()
                ).subscribe();
            }
        };
    }
*/
}
