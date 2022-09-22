package com.simform.invoicingsystem.entity;

import com.simform.invoicingsystem.validation.NameValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NameValidation
    private String name;
    @Column(length = 100)
    private String email;
    @Column(length = 20)
    private String city;
    @Column(length = 20)
    private String state;
    @Column(length = 20)
    private String country;
    @Column(length = 20)
    private String phoneNumber;
    private LocalDateTime createdAt;
    @Column(length = 40)
    private String createdBy;
    private LocalDateTime updatedAt;
    @Column(length = 40)
    private String updatedBy;
    private LocalDateTime deletedAt;
    @Column(length = 40)
    private String deletedBy;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER, targetEntity = Company.class)
    private Company Company;
}
