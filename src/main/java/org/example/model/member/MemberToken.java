package org.example.model.member;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberToken {

    private String email;
    private String memberToken;

}
