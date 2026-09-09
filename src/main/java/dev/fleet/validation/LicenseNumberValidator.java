package dev.fleet.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class LicenseNumberValidator implements ConstraintValidator<ValidLicenseNumber, String> {

    private static final Pattern LICENSE_NUMBER_PATTERN = Pattern.compile("[A-Z0-9]{6,15}");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        return LICENSE_NUMBER_PATTERN.matcher(value).matches();
    }
}
