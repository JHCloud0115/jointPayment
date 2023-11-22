package org.example.model.member;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginFail  {

    private String loginFailUid; //회원이메일
    private Integer tryCount;
    private LocalDateTime editedAt;

}
