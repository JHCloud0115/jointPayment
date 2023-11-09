package org.example.service.member;

import org.example.model.response.TokenResponse;
import org.example.model.response.member.LoginResp;
import org.springframework.http.ResponseEntity;

public interface MemberLoginService {

    ResponseEntity<TokenResponse> signIn(String email, String password) throws Exception;




}
