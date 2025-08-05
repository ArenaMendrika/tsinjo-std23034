package com.hei.school.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Help {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Beneficiary beneficiary;

  @OneToOne(cascade = CascadeType.ALL)
  private Payment payment;

  private String description;
}
