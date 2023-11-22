package org.example.model.response.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
public class MemberLoginFailResp {

    private int failCnt;
    private String loginFailUid;
    private Integer tryCount;
    private LocalDateTime editedAt;


}
