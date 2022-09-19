package com.simform.invoicingsystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<NameValidation,String > {

    private final String regex="^[A-Za-z][\\w|\\W]{2,29}$";

    @Override
    public void initialize(NameValidation nameValidation) {
        ConstraintValidator.super.initialize(nameValidation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println(value);
        return Pattern.compile(regex).matcher(value).matches() && value.trim().length()>0 && value != null;

    }
}
