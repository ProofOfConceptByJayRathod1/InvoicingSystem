package com.simform.invoicingsystem.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Constraint(validatedBy = NameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface NameValidation {

    String message() default "Name must of 3-30 characters long";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
