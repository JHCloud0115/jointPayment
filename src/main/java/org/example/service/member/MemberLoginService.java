package org.example.service.member;

import org.example.model.member.LoginFail;
import org.example.model.response.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface MemberLoginService {

    boolean loginInCnt(String email, String password) throws Exception;

    ResponseEntity<String> logOut(String email) throws Exception;

}
