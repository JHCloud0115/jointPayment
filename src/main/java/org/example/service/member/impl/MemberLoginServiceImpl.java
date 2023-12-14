package org.example.service.member.impl;

import org.example.common.constants.ApplicationConstants;
import org.example.common.util.SHA256;
import org.example.common.util.TokenProvider;
import org.example.exception.errorCode.CommonErrorCode;
import org.example.exception.exception.RestApiException;
import org.example.mapper.member.MemberLoginFailMapper;
import org.example.mapper.member.MemberLoginMapper;
import org.example.mapper.member.MemberMapper;
import org.example.mapper.member.MemberTokenMapper;
import org.example.model.member.LoginFail;
import org.example.model.member.Member;
import org.example.model.member.MemberToken;
import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.TokenResponse;
import org.example.model.response.member.MemberLoginFailResp;
import org.example.service.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class MemberLoginServiceImpl implements MemberLoginService {

    private MemberMapper memberMapper;
    private MemberLoginFailMapper memberLoginFailMapper;
    private MemberTokenMapper memberTokenMapper;
    private MemberLoginMapper memberLoginMapper;
    private TokenProvider tokenProvider;
    private AuthenticationManagerBuilder authenticationManagerBuilder;



    @Autowired
    public MemberLoginServiceImpl(
            MemberMapper memberMapper,
            TokenProvider tokenProvider,
            MemberLoginFailMapper memberLoginFailMapper,
            MemberTokenMapper memberTokenMapper,
            MemberLoginMapper memberLoginMapper,
            AuthenticationManagerBuilder authenticationManagerBuilder
    ){
        this.memberMapper =memberMapper;
        this.tokenProvider = tokenProvider;
        this.memberLoginFailMapper = memberLoginFailMapper;
        this.memberTokenMapper = memberTokenMapper;
        this.memberLoginMapper = memberLoginMapper;
        this.authenticationManagerBuilder =authenticationManagerBuilder;

    }


    /**
     * 로그인 횟수 확인
     *
     **/
    @Override
    public boolean loginInCnt(MemberPasswordReq memberPasswordReq) throws Exception {

            Member member = memberMapper.selectMemberByEmail(memberPasswordReq.getEmail());

            if(Objects.isNull(member)){
                throw new RestApiException(CommonErrorCode.NOT_FOUND);
            }


            if (member.getPassword() == null || ! member.getPassword().equals(SHA256.encrypt(memberPasswordReq.getPassword()))) {
                throw new RestApiException(CommonErrorCode.NOT_FOUND);
            }

            MemberLoginFailResp memberLoginFailResp = memberLoginFailMapper.selectMemberLoginFailCnt(member.getEmail());

            LoginFail loginFail = new LoginFail();
            loginFail.setLoginFailUid(member.getEmail());
            loginFail.setTryCount(memberLoginFailResp.getTryCount());

            if (loginFail.getTryCount() > ApplicationConstants.PASSWORD_FAILL_LOCK) {
                memberLoginMapper.updateMemberBlock(member.getMemberUid());
                throw new Exception("Blocked");
            } else if (loginFail.getTryCount() == ApplicationConstants.ZERO) {
                memberLoginFailMapper.insertLoginFail(loginFail);
            } else {
                memberLoginFailMapper.updateLoginFailCount(loginFail);
            }
            return true;
        }

    /**
     * 토큰 생성
     *
     */
    public TokenResponse createToken(MemberPasswordReq memberPasswordReq) throws Exception{

        // 토큰 생성
        MemberToken memberToken = new MemberToken();
        memberToken.setAccessToken(tokenProvider.createAccessToken(memberPasswordReq.getEmail()));
        memberToken.setRefreshToken(tokenProvider.createRefreshToken(memberPasswordReq.getEmail()));
        memberToken.setEmail(memberPasswordReq.getEmail());
        memberTokenMapper.insertMemberToken(memberToken);

        return TokenResponse.builder()
                            .refreshToken(memberToken.getRefreshToken())
                            .accessToken(memberToken.getAccessToken())
                            .build();
    }



    /**
     * 로그아웃
     *
     **/

    @Override
    public boolean logOut(HttpServletRequest request, String email) throws Exception {
        String memberToken = request.getHeader("Authorization");

        if (memberToken != null && memberToken.startsWith("Bearer ")) {
            String token = memberToken.substring(7);

            try {
               String username= tokenProvider.validateToken(token);

                if (username != null) {
                    int checkEmail = memberMapper.selectMemberIdCheck(username);
                    if (checkEmail > ApplicationConstants.ZERO) {
                        memberTokenMapper.deleteTokenByEmail(username);
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
            }
        }

        throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
    }
}
