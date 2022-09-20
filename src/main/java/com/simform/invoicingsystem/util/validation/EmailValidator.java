package com.simform.invoicingsystem.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {


    final String regexPattern = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";

    @Override
    public void initialize(EmailValidation emailValidation) {
        ConstraintValidator.super.initialize(emailValidation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.compile(regexPattern)
                .matcher(value)
                .matches();
    }
}
