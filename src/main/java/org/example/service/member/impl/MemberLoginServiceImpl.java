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
     * 로그인
     *
     *
     **/
    @Override
    public ResponseEntity<TokenResponse> loginIn(String email, String password) throws Exception {

        Member member = memberMapper.selectMemberByEmail(email);
        SHA256 sha256 = new SHA256();

        if (member.getPassword() != null) {
            MemberLoginFailResp memberLoginFailResp = memberLoginFailMapper.selectMemberLoginFailCnt(email);

            try {
                if (memberLoginFailResp != null) {
                    LoginFail loginFail = LoginFail.builder()
                            .tryCount(memberLoginFailResp.getFailCnt())
                            .build();

                    if (memberLoginFailResp.getFailCnt() > ApplicationConstants.PASSWORD_FAILL_LOCK) {
                        memberLoginMapper.updateMemberBlock(member.getMemberUid());
                        throw new Exception("비밀번호가 유효하지 않습니다.");
                    } else if (memberLoginFailResp.getFailCnt() == ApplicationConstants.ONE) {
                        memberLoginFailMapper.insertLoginFail(loginFail);
                    } else {
                        memberLoginFailMapper.updateLoginFailCount(loginFail);
                    }
                } else {
                    throw new Exception("로그인 실패 정보를 가져올 수 없습니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("로그인 실패 처리 중 오류 발생");
            }
        }

        MemberToken storedToken = memberTokenMapper.selectMemberTokenByEmail(email);
        if(storedToken == null){
            // Access Token 및 Refresh Token 생성
            String accessToken = tokenProvider.createAccessToken(email);
            String refreshToken = tokenProvider.createRefreshToken(email);

            MemberToken memberToken = new MemberToken();
            memberToken.setRefreshToken(refreshToken);
            memberToken.setAccessToken(accessToken);
            memberToken.setEmail(email);
            memberTokenMapper.insertMemberToken(memberToken);

            return ResponseEntity.ok()
                    .header("Set-Cookie", refreshToken.toString())
                    .body(new TokenResponse("Login successful.", accessToken, refreshToken));
        }

        if (member.getPassword() != null && member.getPassword().equals(sha256.encrypt(password))) {
            try {
                // 회원 확인하고 새로운 Token 생성
                String accessToken = tokenProvider.createAccessToken(email);
                String newRefreshToken = tokenProvider.createRefreshToken(email);

                // 기존 토큰 업데이트
                MemberTokenReq memberTokenReq =new MemberTokenReq();
                memberTokenReq.setAccessToken(accessToken);
                memberTokenReq.setRefreshToken(newRefreshToken);
                memberTokenMapper.upateMemberToken(memberTokenReq);

                // 새로운 Refresh Token을 HttpOnly 쿠키에 저장
                Cookie refreshTokenCookie = new Cookie("refreshToken", newRefreshToken);
                refreshTokenCookie.setHttpOnly(true);

                // Secure 속성은 HTTPS 연결에서만 쿠키를 전송하도록 설정
                refreshTokenCookie.setSecure(true);

                return ResponseEntity.ok()
                        .header("Set-Cookie", refreshTokenCookie.toString())
                        .body(new TokenResponse("Login successful.", accessToken, newRefreshToken));


            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TokenResponse("Failed to generate token.", null, null));
            }
        } else {
            // 인증 실패 응답 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Authentication failed.", null, null));
        }
    }


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
