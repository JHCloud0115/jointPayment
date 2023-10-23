package org.example.common.annotation.validator;

import org.example.common.annotation.EmailValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z](?:[a-zA-Z-]*[a-zA-Z])?@[a-zA-Z]+\\.+$ ");

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && EMAIL_PATTERN.matcher(value).matches();
    }
}