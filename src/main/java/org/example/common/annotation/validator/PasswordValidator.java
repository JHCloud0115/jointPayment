package org.example.common.annotation.validator;

import org.example.common.annotation.PasswordValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.{8,})$\n");

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && EMAIL_PATTERN.matcher(value).matches();
    }
}