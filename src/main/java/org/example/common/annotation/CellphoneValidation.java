package org.example.common.annotation;

import org.example.common.annotation.validator.CellphoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CellphoneValidator.class)
@Documented
public @interface CellphoneValidation {
    String message() default "숫자만 입력해주세요";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
