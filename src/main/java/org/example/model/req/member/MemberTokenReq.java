package org.example.model.req.member;

import lombok.Getter;
import lombok.Setter;
import org.example.common.annotation.CellphoneValidation;
import org.example.common.annotation.EmailValidation;
import org.example.common.annotation.NameValidation;
import org.example.common.annotation.PasswordValidation;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberTokenReq {

    private String refreshToken;
    private String accessToken;
    private LocalDateTime updatedAt;
}
