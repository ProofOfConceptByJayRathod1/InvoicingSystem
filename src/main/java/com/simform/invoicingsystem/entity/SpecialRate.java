package com.simform.invoicingsystem.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "special_rates")
public class SpecialRate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String zohoUserId;
  private String rate = "0";
  private LocalDateTime createdAt;
  @Column(length = 40)
  private String createdBy;
  private LocalDateTime deletedAt;
  @Column(length = 40)
  private String deletedBy;
  private Boolean isDeleted;
}
