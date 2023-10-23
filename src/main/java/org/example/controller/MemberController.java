package org.example.controller;

import org.example.model.CommonResponse;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.member.Member;
import org.example.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController()
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/test")
    public CommonResponse<Void> selectMember() throws Exception {
        String test = "test";
        return new CommonResponse<>();
    }

    @GetMapping("/All")
    public List<Member> selectMembers() throws Exception {
        return memberService.selectMembers();
    }

    @PostMapping("/new")
    public void insertMember(Member member) throws Exception {
        memberService.insertMember(member);
    }

    @PostMapping
    public CommonResponse<Void> insertMember2(@RequestBody @Valid MemberInsertReq memberInsertReq) throws Exception {
        if(!memberInsertReq.getPassword().equals(memberInsertReq.getPasswordCheck())){
            throw new Exception("비밀번호가 일치하지 않습니다");
        }
        memberService.insertMember2(memberInsertReq);
        return new CommonResponse<>();
    }


}
