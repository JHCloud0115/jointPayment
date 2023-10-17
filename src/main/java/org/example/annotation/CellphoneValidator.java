package org.example.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CellphoneValidator implements ConstraintValidator<CellphoneValidation, String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[0-9]*[01]?[0-9]*$");

    @Override
    public void initialize(CellphoneValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && EMAIL_PATTERN.matcher(value).matches();
    }
}