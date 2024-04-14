package com.example.user.livre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
@Table("livre")
public class Livre {

  @Id
  private Long id;
  private String titre_livre;
  private String nom_auteur;
  private int nbr_exemplaire;

}
