package org.example.common.annotation;

import org.example.common.annotation.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface PasswordValidation {
    String message() default "대문자 한개와 특수문자 한개이상을 포함하는 길이가 8자리 이상으로 입력해주시기 바랍니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
