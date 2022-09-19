package com.simform.invoicingsystem.dto;

import org.springframework.validation.annotation.Validated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class SignInRequest {
    private String email;
    private String password;
}
