package org.example.model.member;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginFail  {

    private int loginFailUid; //memberUid
    private Integer tryCount;
    private LocalDateTime editedAt;

}
