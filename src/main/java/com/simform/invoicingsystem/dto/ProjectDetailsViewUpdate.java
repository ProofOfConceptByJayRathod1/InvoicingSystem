package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailsViewUpdate {
    private String name;
    private String model;
    private String clientName;
    private String companyName;
    private String csm;
    private String defaultRate;
}