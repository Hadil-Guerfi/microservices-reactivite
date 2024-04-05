package com.example.emprunt.emprunt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Table("emprunt")
public class Emprunt {

  @Id
  private Long id;
  private Long id_empruteur;
  private Long id_livre;
  private Date date_debut;
  private Date date_fin;

}
