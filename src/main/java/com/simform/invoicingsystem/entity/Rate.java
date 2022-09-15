package com.simform.invoicingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(max = 25)
    private String stack;
    private long techId;
    private LocalDateTime createdAt;
    @Size(max = 40)
    private String createdBy;
    private LocalDateTime updatedAt;
    @Size(max = 40)
    private String updatedBy;
    private LocalDateTime deletedAt;
    @Size(max = 40)
    private String deletedBy;
}
