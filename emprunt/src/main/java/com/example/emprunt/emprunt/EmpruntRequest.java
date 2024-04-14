package com.example.emprunt.emprunt;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EmpruntRequest {

  private Long id_empruteur;
  private Long id_livre;
  private LocalDate date_debut;
  private LocalDate date_fin;
}
