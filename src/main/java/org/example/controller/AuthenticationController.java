package org.example.controller;

import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.TokenResponse;
import org.example.service.member.MemberLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public TokenResponse login(@RequestBody @Valid MemberPasswordReq memberPasswordReq) throws Exception {
       memberLoginService.loginInCnt(memberPasswordReq);
       return memberLoginService.createToken(memberPasswordReq);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(@RequestParam("email") String email) throws Exception {
        memberLoginService.logOut(email);
        return ResponseEntity.status(HttpStatus.OK).body("Logout successful.");
    }


}
