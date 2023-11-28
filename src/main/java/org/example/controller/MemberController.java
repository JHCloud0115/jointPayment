package org.example.controller;

import org.example.common.util.AES256;
import org.example.common.util.TokenProvider;
import org.example.model.CommonResponse;
import org.example.model.member.Mail;
import org.example.model.member.Member;
import org.example.model.req.member.MemberFindReq;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.req.member.MemberUpdateReq;
import org.example.model.response.member.MemberEmailResponse;
import org.example.service.member.EmailService;
import org.example.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;
    private final TokenProvider tokenProvider;

    @Autowired
    public MemberController(
            MemberService memberService,
            EmailService emailService,
            TokenProvider tokenProvider) {
        this.memberService = memberService;
        this.emailService = emailService;
        this.tokenProvider =tokenProvider;
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
    public ResponseEntity<?> findPassword(@RequestBody @Valid MemberFindReq memberFindReq) throws Exception {

        if(memberFindReq.getEmail()==null){
            throw new Exception("이메일을 입력해주세요");
        }

        AES256 aes256 = new AES256();
        memberFindReq.setMemberName(aes256.encrypt(memberFindReq.getMemberName().trim()));
        memberFindReq.setCellphone(aes256.encrypt(memberFindReq.getCellphone().trim()));
        Integer  check = memberService.selectMemberMemberCheck(memberFindReq);
        if(check == null){
            throw  new Exception("등록된 이메일이 아닙니다.");
        }

        Mail mail = emailService.createMailAndChangePassword(memberFindReq.getEmail());
        emailService.mailSend(mail);
        return ResponseEntity.ok().body("이메일로 임시 비밀번호가 발송되었습니다.");
    }


    @PostMapping("/mypage")
    public boolean updateMypage(@RequestBody @Valid MemberUpdateReq memberUpdateReq, HttpServletRequest request) throws Exception {
        String memberToken = request.getHeader("Authorization");

        if (memberToken != null && memberToken.startsWith("Bearer ")) {
            String token = memberToken.substring(7);

            String email= tokenProvider.validateToken(token);
            Member member = memberService.selectMemberByEmail(email); // 토큰에서 회원 이메일 확인해서 전달

            if(member !=null){
                if(memberUpdateReq.getPassword().equals(memberUpdateReq.getPasswordCheck())){
                    memberService.updateMypage(memberUpdateReq,email);
                    return true;
                }

            }
        }
        return false; // 맞는건지 확인 필요..

    }

}
