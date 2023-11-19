package org.example.controller;

import org.example.common.util.TokenProvider;
import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.TokenResponse;
import org.example.service.member.MemberLoginService;
import org.example.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final MemberLoginService memberLoginService;

    @Autowired
    public AuthenticationController(
            MemberLoginService memberLoginService) {
        this.memberLoginService = memberLoginService;
    }


    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid MemberPasswordReq memberPasswordReq) throws Exception {
//        return memberLoginService.loginIn(memberPasswordReq.getEmail(), memberPasswordReq.getPassword());
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(@RequestParam("email") String email) throws Exception {
        memberLoginService.logOut(email);
        return ResponseEntity.status(HttpStatus.OK).body("Logout successful.");
    }


}
