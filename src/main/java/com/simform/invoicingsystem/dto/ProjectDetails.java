package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetails {
    private String name;
    private String model;
    private ClientDetails clientDetails;
    private BillingDetails billingDetails;
    private OtherDetails otherDetails;
}
