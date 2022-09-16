package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectView {

    private String name;
    private String model;
    private String clientName;
    private String email;
    private String invoiceCycle;
    private String payModel;
    private String accType;
}
