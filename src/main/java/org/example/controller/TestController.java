package org.example.controller;

import org.example.model.CommonResponse;
import org.example.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private MemberService memberService;

    @Autowired
    public TestController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/token")
    public CommonResponse<Void> deleteToken(@RequestParam("email") String email) throws Exception{
        memberService.deleteTestMemberToken(email);
        return new CommonResponse<>();
    }

}