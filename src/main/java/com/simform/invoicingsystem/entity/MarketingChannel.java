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
public class MarketingChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(max = 20)
    private String channel;
    private LocalDateTime createdAt;
    @Size(max = 40)
    private  String createdBy;
    private LocalDateTime updatedAt;
    @Size(max = 40)
    private String updatedBy;
    private LocalDateTime deletedAt;
    @Size(max = 40)
    private String deletedBy;
}
