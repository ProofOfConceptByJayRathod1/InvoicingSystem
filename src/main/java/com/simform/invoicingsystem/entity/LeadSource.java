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

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Table(name="lead_sources")
public class LeadSource {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(length=20)
    private String source;
    private LocalDateTime createdAt;
    @Column(length=40)
    private String createdBy;
    private LocalDateTime updatedAt;
    @Column(length=40)
    private String updatedBy;
    private LocalDateTime deletedAt;
    @Column(length=40)
    private String deletedBy;

}
