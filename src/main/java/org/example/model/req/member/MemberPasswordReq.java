package org.example.model.req.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class MemberPasswordReq {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
