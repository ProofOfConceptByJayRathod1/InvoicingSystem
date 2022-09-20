package com.simform.invoicingsystem.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NotEmptyValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NotEmpty {
    String message() default "Can't be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
