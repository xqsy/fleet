package dev.fleet.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.RECORD_COMPONENT,
        ElementType.PARAMETER
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LicenseNumberValidator.class)
public @interface ValidLicenseNumber {
    String message() default "invalid license number format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
