package com.simform.invoicingsystem.dto;

import com.simform.invoicingsystem.validation.Empty;
import com.simform.invoicingsystem.validation.NameValidation;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetails {

    @Valid
    @NameValidation
    private String name;
    private String model;
    @Valid
    private ClientDetails clientDetails;
    @Valid
    private BillingDetails billingDetails;
    @Valid
    private OtherDetails otherDetails;
}
