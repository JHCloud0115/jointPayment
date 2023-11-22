package org.example.common.annotation;

import org.example.common.annotation.validator.NameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface NameValidation {
    String message() default "한글로 입력해주세요";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
