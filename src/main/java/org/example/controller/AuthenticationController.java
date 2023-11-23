package org.example.controller;

import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.TokenResponse;
import org.example.service.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> Stashed changes
>>>>>>> Stashed changes

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    private final MemberLoginService memberLoginService;

    @Autowired
    public AuthenticationController(
            MemberLoginService memberLoginService) {
        this.memberLoginService = memberLoginService;
    }


    @PostMapping("/login")
<<<<<<< Updated upstream
    public TokenResponse login(@RequestBody @Valid MemberPasswordReq memberPasswordReq) throws Exception {
       memberLoginService.loginInCnt(memberPasswordReq);
       return memberLoginService.createToken(memberPasswordReq);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(@RequestParam("email") String email) throws Exception {
        memberLoginService.logOut(email);
        return ResponseEntity.status(HttpStatus.OK).body("Logout successful.");
    }


=======
<<<<<<< Updated upstream
    public ResponseEntity<String> login(@RequestBody @Valid MemberPasswordReq memberPasswordReq) throws Exception {
        MemberPasswordByEmail memberPasswordCheck = memberService.selectMemberPasswordByEmail(memberPasswordReq.getEmail());
        SHA256 sha256 = new SHA256();

        if (memberPasswordCheck != null && memberPasswordCheck.getPassword().equals(sha256.encrypt(memberPasswordReq.getPassword()))) {
            try {
                TokenProvider tokenProvider = new TokenProvider();
                String token = tokenProvider.createToken(memberPasswordReq.getEmail());
                return ResponseEntity.ok(token);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate token.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed.");
        }
    }
=======
    public TokenResponse login(@RequestBody @Valid MemberPasswordReq memberPasswordReq) throws Exception {
       memberLoginService.loginInCnt(memberPasswordReq);
       return memberLoginService.createToken(memberPasswordReq);
    }

    @PostMapping("/logout")
    public boolean logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean result = memberLoginService.logOut(request);
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return result;
    }

    // 게이트웨이 > 서버 전체를 혼자 구축 못해. ㅎㅎㅎ 하면 시니어개발자 할수있음
    // 필터 인터셉터 시큐리티 JWT
    //  게이트웨이 구축하지 않아서 컨트롤러(서비스단)에서 구현

>>>>>>> Stashed changes
>>>>>>> Stashed changes
}
