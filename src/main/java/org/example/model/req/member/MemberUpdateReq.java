package org.example.model.req.member;

import lombok.Getter;
import lombok.Setter;
import org.example.common.annotation.CellphoneValidation;
import org.example.common.annotation.PasswordValidation;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberUpdateReq {
    @NotBlank
    @PasswordValidation(message = "조건을 다시 한 번 확인해주세요")
    // 패스워드 정규식 체크
    private String password;

    @NotBlank
    private String passwordCheck;

    @NotBlank
    @CellphoneValidation(message = "01로 시작하는 숫자만 입력해주세요")
    private String cellphone;

    private String email;

}
