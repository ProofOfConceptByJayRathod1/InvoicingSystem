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
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String companyName;
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

}
