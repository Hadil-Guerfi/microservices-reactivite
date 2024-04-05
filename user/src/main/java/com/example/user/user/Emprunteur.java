package com.example.user.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
@Table("emprunteur")
public class Emprunteur {

  @Id
  private Long id;
  private String firstname;
  private String lastname;
  private String email;

}
