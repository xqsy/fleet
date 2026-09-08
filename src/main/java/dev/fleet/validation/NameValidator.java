package dev.fleet.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<ValidName, String> {

    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-z]+(-[A-Za-z]+)*");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }

        return NAME_PATTERN.matcher(value).matches();
    }
}
