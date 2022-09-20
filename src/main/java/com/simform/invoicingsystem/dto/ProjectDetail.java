package com.simform.invoicingsystem.dto;

import com.simform.invoicingsystem.util.validation.EmailValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ProjectDetail {
    @EmailValidation
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
    private String leadSource;
    private String channel;
    private boolean activeBillingFlag;
}
