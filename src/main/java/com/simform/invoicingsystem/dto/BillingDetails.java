package com.simform.invoicingsystem.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingDetails {
    private String cycle;
    private int invoiceTerm;
    private String payModel;
    private String accType;
    private LocalDate accStartDate;
    private LocalDate projectStartDate;
}
