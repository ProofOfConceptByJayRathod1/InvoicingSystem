package com.simform.invoicingsystem.dto;

import java.time.LocalDate;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetail {
    private String name;
    private String model;
    private ClientDetails clientDetails;
    private String cycle;
    private int invoiceTerm;
    private String payModel;
    private String accType;
    private LocalDate accStartDate;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private String csm;
    private Collection<String> salesPersons;
    private String contractLink;
    private String source;
    private String channel;
    private boolean activeBillingFlag;
}
