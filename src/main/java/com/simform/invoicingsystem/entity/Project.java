package com.simform.invoicingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 10)
    private String projectName;
    private String payModel;
    private int invoiceTerm;
    private LocalDate accStartDate;
    private boolean activeBillingFlag;
    @Column(length = 250)
    private String contractLink;
    private String startDate;
    private String endDate;
    private boolean isActive;
    private LocalDateTime createdAt;
    @Column(length = 40)
    private String createdBY;
    private LocalDateTime updatedAt;
    @Column(length = 40)
    private String updatedBy;
    private LocalDateTime deletedAt;
    @Column(length = 40)
    private String deletedBy;
}
