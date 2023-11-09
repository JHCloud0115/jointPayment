package org.example.service.member.impl;

import org.example.common.constants.ApplicationConstants;
import org.example.common.util.SHA256;
import org.example.common.util.TokenProvider;
import org.example.mapper.member.MemberLoginFailMapper;
import org.example.mapper.member.MemberMapper;
import org.example.mapper.member.MemberTokenMapper;
import org.example.model.member.Member;
import org.example.model.member.MemberToken;
import org.example.model.response.TokenResponse;
import org.example.model.response.member.LoginResp;
import org.example.model.response.member.MemberLoginFailResp;
import org.example.service.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginServiceImpl implements MemberLoginService {

    private MemberMapper memberMapper;
    private MemberLoginFailMapper memberLoginFailMapper;
    private MemberTokenMapper memberTokenMapper;
    private TokenProvider tokenProvider;



    @Autowired
    public MemberLoginServiceImpl(
            MemberMapper memberMapper,
            TokenProvider tokenProvider,
            MemberLoginFailMapper memberLoginFailMapper,
            MemberTokenMapper memberTokenMapper
    ){
        this.memberMapper =memberMapper;
        this.tokenProvider = tokenProvider;
        this.memberLoginFailMapper = memberLoginFailMapper;
        this.memberTokenMapper = memberTokenMapper;

    }

    @Override
    public ResponseEntity<TokenResponse> signIn(String email, String password) throws Exception {

        Member member =memberMapper.selectMemberByEmail(email);
        SHA256 sha256 = new SHA256();

        if(member.getPassword()!=null){
            MemberLoginFailResp memberLoginFailResp = memberLoginFailMapper.selectMemberLoginFailCnt(email);
            if(memberLoginFailResp.getFailCnt()> ApplicationConstants.PASSWORD_FAILL_LOCK){
                memberMapper.updateMemberBlock(member.getMemberUid());
                throw new Exception("비밀번호가 유효하지 않습니다.");
            }
        }

        if (member.getPassword() != null && member.getPassword().equals(sha256.encrypt(password))) {
            try {
                // Access Token 및 Refresh Token 생성
                String accessToken = tokenProvider.createAccessToken(email);
                String refreshToken = tokenProvider.createRefreshToken(email);

                MemberToken memberToken = new MemberToken();
                memberToken.setMemberToken(refreshToken);
                memberToken.setEmail(email);
                memberTokenMapper.insertMemberToken(memberToken);

                return ResponseEntity.ok(new TokenResponse("Login successful.", accessToken, refreshToken));

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TokenResponse("Failed to generate token.", null, null));
            }
        } else {
            // 인증 실패 응답 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Authentication failed.", null, null));
        }
    }



}
