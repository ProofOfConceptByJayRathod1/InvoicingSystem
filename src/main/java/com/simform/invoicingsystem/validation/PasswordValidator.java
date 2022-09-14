package com.simform.invoicingsystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

        /*
             ^                # start-of-string
            (?=.*[0-9])       # a digit must occur at least once
            (?=.*[a-z])       # a lower case letter must occur at least once
            (?=.*[A-Z])       # an upper case letter must occur at least once
            (?=.*[@#$%^&+=])  # a special character must occur at least once
            (?=\S+$)          # no whitespace allowed in the entire string
            .{8,}             # anything, at least eight places though
            $                 # end-of-string
         */
    final String regexPattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Override
    public void initialize(PasswordValidation passwordValidation) {
        ConstraintValidator.super.initialize(passwordValidation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.compile(regexPattern)
                .matcher(value)
                .matches();
    }
}
