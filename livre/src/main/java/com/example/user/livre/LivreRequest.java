package com.example.livre.livre;

import lombok.Data;

@Data
public class LivreRequest {

  private String titre_livre;
  private String nom_auteur;
  private int nbr_exemplaire;

}
