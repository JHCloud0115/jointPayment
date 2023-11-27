package org.example.controller;

import org.example.common.util.AES256;
import org.example.common.util.SHA256;
import org.example.model.CommonResponse;
import org.example.model.req.member.MemberFindReq;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.member.Member;
import org.example.model.req.member.MemberPasswordReq;
import org.example.model.response.member.MemberEmailResponse;
import org.example.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> findEmail(@RequestBody @Valid MemberFindReq memberFindReq) throws Exception{
        AES256 aes256 = new AES256();

        try {
            memberFindReq.setMemberName(aes256.encrypt(memberFindReq.getMemberName().trim()));
            memberFindReq.setCellphone(aes256.encrypt(memberFindReq.getCellphone().trim()));

            MemberEmailResponse memberEmail = memberService.selectMemberEmail(memberFindReq);

            if (memberEmail == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 이메일이 없습니다");
            } else {
                return ResponseEntity.ok().body(memberEmail);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
        }
    }

    @PostMapping("/find/password")
    public ResponseEntity<?> findPassword(@RequestBody @Valid MemberFindReq memberFindReq) throws Exception{
        AES256 aes256 = new AES256();

        try {
            memberFindReq.setEmail(aes256.encrypt(memberFindReq.getEmail().trim()));
            memberFindReq.setMemberName(aes256.encrypt(memberFindReq.getMemberName().trim()));
            memberFindReq.setCellphone(aes256.encrypt(memberFindReq.getCellphone().trim()));

            MemberEmailResponse memberEmail = memberService.selectMemberEmail(memberFindReq);

            if (memberEmail == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 이메일이 없습니다");
            } else {
                return ResponseEntity.ok().body(memberEmail);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류 발생");
        }
    }

}
