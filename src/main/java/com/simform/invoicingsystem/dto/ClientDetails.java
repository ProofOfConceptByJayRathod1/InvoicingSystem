package com.simform.invoicingsystem.dto;

import com.simform.invoicingsystem.validation.EmailValidation;
import com.simform.invoicingsystem.validation.NameValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDetails {

    @NameValidation
    private String name;
    @NameValidation
    private String companyName;
    @EmailValidation
    private String email;
    @NameValidation
    private String city;
    private String state;
    private String country;
    private String phoneNumber;
}
