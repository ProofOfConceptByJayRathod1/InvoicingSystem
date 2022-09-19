package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDetailsView {
    private String projectName;
    private String projectModel;
    private String csmName;
    private String clientName;
    private String companyName;
    private int defaultRate;
    Collection<TechnologyDetails> technologyDetails;
    Collection<SpecialRateDetails> specialRateDetails;
}
