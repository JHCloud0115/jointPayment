package org.example.service.member.impl;

import org.example.common.constants.ApplicationConstants;
import org.example.common.util.SHA256;
import org.example.common.util.TokenProvider;
import org.example.mapper.member.MemberLoginFailMapper;
import org.example.mapper.member.MemberLoginMapper;
import org.example.mapper.member.MemberMapper;
import org.example.mapper.member.MemberTokenMapper;
import org.example.model.member.LoginFail;
import org.example.model.member.Member;
import org.example.model.member.MemberToken;
import org.example.model.req.member.MemberTokenReq;
import org.example.model.response.TokenResponse;
import org.example.model.response.member.LoginResp;
import org.example.model.response.member.MemberLoginFailResp;
import org.example.service.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class MemberLoginServiceImpl implements MemberLoginService {

    private MemberMapper memberMapper;
    private MemberLoginFailMapper memberLoginFailMapper;
    private MemberTokenMapper memberTokenMapper;
    private MemberLoginMapper memberLoginMapper;
    private TokenProvider tokenProvider;



    @Autowired
    public MemberLoginServiceImpl(
            MemberMapper memberMapper,
            TokenProvider tokenProvider,
            MemberLoginFailMapper memberLoginFailMapper,
            MemberTokenMapper memberTokenMapper,
            MemberLoginMapper memberLoginMapper
    ){
        this.memberMapper =memberMapper;
        this.tokenProvider = tokenProvider;
        this.memberLoginFailMapper = memberLoginFailMapper;
        this.memberTokenMapper = memberTokenMapper;
        this.memberLoginMapper = memberLoginMapper;

    }


    /**
     * 로그인 횟수 확인
     *
     **/
    @Override
    public boolean loginInCnt(String email, String password) throws Exception {

            Member member = memberMapper.selectMemberByEmail(email);
            SHA256 sha256 = new SHA256();

            if (member.getPassword() == null) {
                throw new Exception("Check Password");
            }
            if(member.getPassword().equals(sha256.encrypt(password))){
                throw new Exception("Check Password");
            }

            MemberLoginFailResp memberLoginFailResp = memberLoginFailMapper.selectMemberLoginFailCnt(email);

            LoginFail loginFail = new LoginFail();
            loginFail.setLoginFailUid(member.getMemberUid());
            loginFail.setTryCount(memberLoginFailResp.getTryCount());

            if (loginFail.getTryCount() > ApplicationConstants.PASSWORD_FAILL_LOCK) {
                memberLoginMapper.updateMemberBlock(member.getMemberUid());
                throw new Exception("계정 잠금 처리 되었습니다.");
            } else if (loginFail.getTryCount() == ApplicationConstants.ZERO) {
                memberLoginFailMapper.insertLoginFail(loginFail);
            } else {
                memberLoginFailMapper.updateLoginFailCount(loginFail);
            }
            return true;
        }

    /**
     * 토큰 확인 및 refreshToken 적용
     *
      */


    /**
     * 로그아웃
     *
     */
    @Override
    public ResponseEntity<String> logOut(String email) throws Exception {
        memberTokenMapper.expireRefreshToken(email);
        return ResponseEntity.status(HttpStatus.OK).body("Logout successful.");
    }




}
