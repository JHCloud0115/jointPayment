package org.example.controller;

import org.example.model.CommonResponse;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.member.Member;
import org.example.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@RestController
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

    @GetMapping("/all")
    public List<Member> selectMembers() throws Exception {
        return memberService.selectMembers();
    }

    @GetMapping("/email/check")
    public ResponseEntity<Integer> selectMemberEmailCheck(@RequestParam("email") String email) throws Exception{
        int emailResult =memberService.selectMemberEmailCheck(email);
        return ResponseEntity.ok(emailResult);
    }


    @PostMapping("/regist")
    public CommonResponse<Void> insertMember2(@RequestBody @Valid MemberInsertReq memberInsertReq) throws Exception {

        int emailResult = memberService.selectMemberEmailCheck(memberInsertReq.getEmail());

        if(emailResult == 0){
            if(!memberInsertReq.getPassword().equals(memberInsertReq.getPasswordCheck())){
                throw new Exception("Check Password");
            }
            memberService.insertMember2(memberInsertReq);
        }
        return new CommonResponse<>();
    }




}
