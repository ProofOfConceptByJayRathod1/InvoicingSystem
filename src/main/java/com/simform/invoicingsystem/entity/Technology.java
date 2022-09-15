package com.simform.invoicingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 40)
    private String name;
    private LocalDateTime createdAt;
    @Column(length = 40)
    private String createBy;
    private LocalDateTime updatedAt;
    @Column(length = 40)
    private String updateBy;
    private LocalDateTime deletedAt;
    @Column(length = 40)
    private String deletedBy;
}
