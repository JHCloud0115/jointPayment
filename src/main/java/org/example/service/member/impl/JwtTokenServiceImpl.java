package org.example.service.member.impl;

import io.jsonwebtoken.Header;
import org.example.common.util.TokenProvider;
import org.example.mapper.member.MemberMapper;
import org.example.model.member.Member;
import org.example.model.response.LoginResp;
import org.example.service.member.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
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
    public LoginResp signIn(String email, String password, Header header, Locale locale) throws Exception {

        Member member =memberMapper.selectMemberByEmail(email);

        LoginResp loginResp = LoginResp.builder()
                                .memberUid(member.getMemberUid())
                                .build();

        return loginResp;
    }

    @Override
    public LoginResp loginProcess(String email, String password, String ip) throws Exception {

        LoginResp loginResp = new LoginResp();
        String jwtToken = tokenProvider.createToken(email);

        loginResp.setToken(jwtToken);
        loginResp.setEmail(email);


        return null;
    }
}
