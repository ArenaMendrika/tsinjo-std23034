package com.hei.school.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String pspPaymentId;
  private String pspType;
  private int amount;
  private String status;
  private LocalDateTime creationDate;
  private String payerEmail;
}
