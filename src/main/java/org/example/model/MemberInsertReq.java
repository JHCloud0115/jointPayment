package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.annotation.CellphoneValidation;
import org.example.annotation.EmailValidation;
import org.example.annotation.NameValidation;
import org.example.annotation.PasswordValidation;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberInsertReq {

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
    private String name;

    @NotBlank
    @CellphoneValidation(message = "01로 시작하는 숫자만 입력해주세요")
    private String cellphone;

}
