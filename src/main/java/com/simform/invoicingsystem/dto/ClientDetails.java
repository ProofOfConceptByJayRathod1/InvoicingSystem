package com.simform.invoicingsystem.dto;

import com.simform.invoicingsystem.validation.EmailValidation;
import com.simform.invoicingsystem.validation.NameValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetails {

    @NotEmpty
    @NameValidation
    private String name;
    @NotEmpty
    @NameValidation
    private String companyName;
    @EmailValidation
    @NotEmpty
    private String email;
    @NotEmpty
    @NameValidation
    private String city;
    private String state;
    private String country;
    @Pattern(regexp = "^[+]?\\d{5,15}$")
    private String phoneNumber;
}
