package org.example.model.member;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginFail  {

    private String loginFailUid;
    private String serviceCode;
    private String email;
    private String ip;
    private Integer tryCount;
    private LocalDateTime editedAt;

}
