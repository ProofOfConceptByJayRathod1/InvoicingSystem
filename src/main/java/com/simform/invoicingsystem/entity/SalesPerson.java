package com.simform.invoicingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Table(name = "sales_persons")
public class SalesPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String name;
    private LocalDateTime createdAt;
    @Column(length = 40)
    private String createdBy;
    private LocalDateTime updatedAt;
    @Column(length = 40)
    private String updatedBy;
    private LocalDateTime deletedAt;
    @Column(length = 40)
    private String deletedBy;

    public SalesPerson(String abc) {
        this.name = abc;
    }
}
