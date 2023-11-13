package org.example.model.member;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberToken {
    private int id;
    private String email;
    private String refreshToken;
    private String accessToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
