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
public class MemberInsertReq {

    private Long memberUid;

    @NotBlank
    @EmailValidation(message = "영문으로 시작하는 올바른 이메일 형식으로 입력해주세요")
    private String email;

    @NotBlank
    @PasswordValidation(message = "조건을 다시 한 번 확인해주세요")
    // 패스워드 정규식 체크
    private String password;

    @NotBlank
    private String passwordCheck;

    @NotBlank
    @NameValidation(message ="한글로 입력해주세요")
    private String memberName;

    @NotBlank
    @CellphoneValidation(message = "01로 시작하는 숫자만 입력해주세요")
    private String cellphone;

    private LocalDateTime createdAt;

}
