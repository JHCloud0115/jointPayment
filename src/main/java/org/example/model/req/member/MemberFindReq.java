package org.example.model.req.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberFindReq {
    @NotBlank
    private String memberName;
    @NotBlank
    private String cellphone;
    private String email;

}
