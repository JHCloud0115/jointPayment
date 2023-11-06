package org.example.controller;

import org.example.common.util.SHA256;
import org.example.common.util.TokenProvider;
import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.MemberPassword;
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

    @Autowired
    public AuthenticationController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid MemberPasswordReq memberPasswordReq) throws Exception {
        MemberPassword memberPasswordCheck = memberService.selectMemberPasswordByEmail(memberPasswordReq.getEmail());
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
}
