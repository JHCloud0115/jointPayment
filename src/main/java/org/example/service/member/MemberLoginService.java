package org.example.service.member;

import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.TokenResponse;

<<<<<<< Updated upstream
=======
import javax.servlet.http.HttpServletRequest;

>>>>>>> Stashed changes
public interface MemberLoginService {

    boolean loginInCnt(MemberPasswordReq memberPasswordReq) throws Exception;

    TokenResponse createToken(MemberPasswordReq memberPasswordReq) throws Exception;

<<<<<<< Updated upstream
    void logOut(String email) throws Exception;
=======
    boolean logOut(HttpServletRequest request) throws Exception;
>>>>>>> Stashed changes

}
