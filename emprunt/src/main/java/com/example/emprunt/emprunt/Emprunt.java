package com.example.emprunt.emprunt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Table("emprunt")
public class Emprunt {

  @Id
  private Long id;
  private Long id_emprunteur;
  private Long id_livre;
  private LocalDate date_debut;
  private LocalDate date_fin;

}
