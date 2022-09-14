package com.simform.invoicingsystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {


    /*
    It allows numeric values from 0 to 9.
    Both uppercase and lowercase letters from a to z are allowed.
    Allowed are underscore “_”, hyphen “-“, and dot “.”
    Dot isn't allowed at the start and end of the local part.
    Consecutive dots aren't allowed.
    For the local part, a maximum of 64 characters are allowed.
    */
    final String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";

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
