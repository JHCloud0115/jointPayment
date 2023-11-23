package org.example.service.member;

import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.TokenResponse;

import javax.servlet.http.HttpServletRequest;

public interface MemberLoginService {

    boolean loginInCnt(MemberPasswordReq memberPasswordReq) throws Exception;

    TokenResponse createToken(MemberPasswordReq memberPasswordReq) throws Exception;

    boolean logOut(HttpServletRequest request) throws Exception;

}
