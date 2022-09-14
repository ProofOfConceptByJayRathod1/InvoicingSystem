package com.simform.invoicingsystem.dto;

import com.simform.invoicingsystem.validation.EmailValidation;
import com.simform.invoicingsystem.validation.PasswordValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class SignInRequest {

  @EmailValidation
  private String email;

  @PasswordValidation
  private String password;
}
