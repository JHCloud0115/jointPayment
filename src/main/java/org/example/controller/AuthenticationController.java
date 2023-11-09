package org.example.controller;

import org.example.common.util.SHA256;
import org.example.common.util.TokenProvider;
import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.member.MemberPassword;
import org.example.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthenticationController(
            MemberService memberService,
            TokenProvider tokenProvider
    ) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid MemberPasswordReq memberPasswordReq) throws Exception {
        return null;
    }

    // 토큰 체크 api
    // 화면에서 보내준 토큰값 받아오기
    // 토큰 저장 및 비교 --> 쿠키값을 헤더에 담아서 전달
    // 화면마다 토큰 필요 유무에 따른 구현 방법
    // ㄴ-- 어떤 방식 썼는지
    // 로그아웃은 어떻게?
}
