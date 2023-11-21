package org.example.service.member;

import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.TokenResponse;

public interface MemberLoginService {

    boolean loginInCnt(MemberPasswordReq memberPasswordReq) throws Exception;

    TokenResponse createToken(MemberPasswordReq memberPasswordReq) throws Exception;

    void logOut(String email) throws Exception;

}
