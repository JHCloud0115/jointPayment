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

//    @PostMapping("/password")
//    public Boolean selectMemberPasswordByEmail(@RequestBody @Valid MemberPasswordReq memberPasswordReq)throws Exception{
//        MemberPasswordByEmail memberPasswordCheck = memberService.selectMemberPasswordByEmail(memberPasswordReq.getEmail());
//        SHA256 sha256 = new SHA256();
//
//        if (memberPasswordCheck != null) {
//            if (memberPasswordCheck.getPassword().equals(sha256.encrypt(memberPasswordReq.getPassword()))) {
//                return true;
//            }
//        }
//        return false;
//    }

    @PostMapping("/regist")
    public CommonResponse<Void> insertMember2(@RequestBody @Valid MemberInsertReq memberInsertReq) throws Exception {

        int emailResult = memberService.selectMemberEmailCheck(memberInsertReq.getEmail());

        if(emailResult == 0){
            if(!memberInsertReq.getPassword().equals(memberInsertReq.getPasswordCheck())){
                throw new Exception("비밀번호가 일치하지 않습니다");
            }
            memberService.insertMember2(memberInsertReq);
        }
        return new CommonResponse<>();
    }

    @GetMapping("/logout")
    // 로그인 상태에서만 로그아웃
    // 쿠키로 로그인 상태인 회원만 로그아웃

    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "/member/login";
    }


}
