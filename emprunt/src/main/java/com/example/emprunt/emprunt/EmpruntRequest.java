package com.example.emprunt.emprunt;

import lombok.Data;

import java.util.Date;

@Data
public class EmpruntRequest {

  private Long id_empruteur;
  private Long id_livre;
  private Date date_debut;
  private Date date_fin;
}
