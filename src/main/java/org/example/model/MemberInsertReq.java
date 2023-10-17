package org.example.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberInsertReq {

    @NotBlank
    // 이메일 정규식 체크
    private String email;
    @NotBlank
    // 패스워드 정규식 체크
    private String password;
    @NotBlank
    private String passwordCheck;
    @NotBlank
    private String name;
    @NotBlank
    // 휴대폰 정규식 체크
    private String cellphone;

}
