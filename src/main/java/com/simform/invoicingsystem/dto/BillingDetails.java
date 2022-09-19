package com.simform.invoicingsystem.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.simform.invoicingsystem.validation.Empty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingDetails {

    private String cycle;
    @Min(value = 0,message = "Can't be negative")
    private int invoiceTerm;
    private String payModel;
    private String accType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate accStartDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate projectStartDate;
}
