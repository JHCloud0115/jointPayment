package org.example.service.member.impl;

import org.example.common.constants.ApplicationConstants;
import org.example.common.util.TokenProvider;
import org.example.mapper.member.MemberMapper;
import org.example.model.member.Member;
import org.example.model.response.LoginResp;
import org.example.model.response.MemberLoginFailResp;
import org.example.service.member.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private MemberMapper memberMapper;
    private TokenProvider tokenProvider;


    @Autowired
    public JwtTokenServiceImpl(
            MemberMapper memberMapper,
            TokenProvider tokenProvider
    ){
        this.memberMapper =memberMapper;
        this.tokenProvider = tokenProvider;

    }

    @Override
    public LoginResp signIn(String email, String password) throws Exception {

        Member member =memberMapper.selectMemberByEmail(email);


        Pattern pattern = Pattern.compile(ApplicationConstants.PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches())
            throw new Exception("비밀번호가 유효하지 않습니다.");

        MemberLoginFailResp memberLoginFailResp =  memberMapper.selectMemberLoginFailCnt(email);

        if(memberLoginFailResp.getFailCnt()>ApplicationConstants.PASSWORD_FAILL_LOCK){
            memberMapper.updateMemberBlock(member.getMemberUid());
        }

        LoginResp loginResp = LoginResp.builder()
                .memberUid(member.getMemberUid())
                .email(email)
                .build();

        return loginResp;
    }

    @Override
    public LoginResp loginProcess(String email, String password,String ip) throws Exception {

        LoginResp loginResp = new LoginResp();
        String jwtToken = tokenProvider.createToken(email);

        loginResp.setToken(jwtToken);
        loginResp.setEmail(email);

        return null;
    }

}
