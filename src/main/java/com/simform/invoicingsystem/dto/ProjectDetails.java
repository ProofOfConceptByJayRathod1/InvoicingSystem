package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetails {
    private String projectName;
    private ProjectModelDetails projectModelDetails;
    private ClientDetails clientDetails;
    private BillingDetails billingDetails;
}
