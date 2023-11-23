package org.example.controller;

import org.example.common.util.SHA256;
import org.example.model.CommonResponse;
import org.example.model.req.member.MemberFindReq;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.member.Member;
import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.member.MemberEmailResponse;
import org.example.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


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

    @PostMapping("/find/email")
    public MemberEmailResponse findEmail(@RequestBody @Valid MemberFindReq memberFindReq) throws Exception{
        if(memberFindReq == null){
            throw new IllegalArgumentException("Plz Check Again");
        } else {
            SHA256 sha256 = new SHA256();
            memberFindReq.setMemberName(sha256.encrypt(memberFindReq.getMemberName()));
            memberFindReq.setCellphone(sha256.encrypt(memberFindReq.getCellphone()));
            return memberService.selectMemberEmail(memberFindReq);
        }
    }



}
