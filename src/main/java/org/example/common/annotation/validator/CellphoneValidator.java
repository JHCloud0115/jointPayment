package org.example.common.annotation.validator;

import org.example.common.annotation.CellphoneValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CellphoneValidator implements ConstraintValidator<CellphoneValidation, String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$\n");

    @Override
    public void initialize(CellphoneValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && EMAIL_PATTERN.matcher(value).matches();
    }
}