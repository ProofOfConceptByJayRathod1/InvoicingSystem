package com.simform.invoicingsystem.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BillingDetails {
    private String cycle;
    private int invoiceTerm;
    private String payModel;
    private String accType;
    private LocalDate accStartDate;
    private LocalDate projectStartDate;
}
