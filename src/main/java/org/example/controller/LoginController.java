package org.example.controller;

import org.example.model.CommonResponse;
import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.TokenResponse;
import org.example.service.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private final MemberLoginService memberLoginService;

    @Autowired
    public LoginController(
            MemberLoginService memberLoginService) {
        this.memberLoginService = memberLoginService;
    }


    @PostMapping("/login")
    public CommonResponse<TokenResponse> login(@RequestBody @Valid MemberPasswordReq memberPasswordReq) throws Exception {
        memberLoginService.loginInCnt(memberPasswordReq);
        TokenResponse token = memberLoginService.createToken(memberPasswordReq);

        return new CommonResponse<>(token);
    }


    @PostMapping("/logout")
    public CommonResponse<Void> logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        memberLoginService.logOut(request);
        // TODO 이주희 : DB toke 삭제
        // TODO 이주희 : CommonResponse 모든 api return 변경

        return new CommonResponse<>();
    }

    // 게이트웨이 > 서버 전체를 혼자 구축 못해. ㅎㅎㅎ 하면 시니어개발자 할수있음
    // 필터 인터셉터 시큐리티 JWT
    //  게이트웨이 구축하지 않아서 컨트롤러(서비스단)에서 구현

}
