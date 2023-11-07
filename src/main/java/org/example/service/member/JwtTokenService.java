package org.example.service.member;

import io.jsonwebtoken.Header;
import org.example.model.response.LoginResp;

import java.util.Locale;

public interface JwtTokenService  {

    LoginResp signIn(String email, String password) throws Exception;

    LoginResp loginProcess(String email, String password,String ip) throws Exception ;

}
