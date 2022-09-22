package com.simform.invoicingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simform.invoicingsystem.validation.NameValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ProjectDetails {
    @NameValidation
    private String name;
    @NotNull
    private String zohoProjectId;
    private String defaultRate;
    private String model;
    @Valid
    private ClientDetails clientDetails;
    private String cycle;
    @Min(0)
    private int invoiceTerm;
    private String payModel;
    private String accType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate accStartDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate projectStartDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MMM-yyyy")
    private LocalDate projectEndDate;
    private String csm;
    private Collection<String> salesPersons;
    private String contractLink;
    private String leadSource;
    private String channel;
    private boolean activeBillingFlag;
    @Valid
    private Collection<TechStackRate> techStackRates;
    private String source;
}
