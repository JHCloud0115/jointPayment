package org.example.controller;

import org.example.common.util.AES256;
import org.example.common.util.TokenProvider;
import org.example.exception.errorCode.CommonErrorCode;
import org.example.exception.exception.RestApiException;
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

    /**
     * 이메일 중복 확인
     * @param email
     * @return
     * @throws Exception
     */
    @GetMapping("/email/check")
    public ResponseEntity<Integer> selectMemberEmailCheck(@RequestParam("email") String email) throws Exception{
        int emailResult =memberService.selectMemberEmailCheck(email);
        return ResponseEntity.ok(emailResult);
    }

    /**
     * 회원가입
     * @param memberInsertReq
     * @return
     * @throws Exception
     */
    @PostMapping("/regist")
    public CommonResponse<Void> insertMember2(@RequestBody @Valid MemberInsertReq memberInsertReq) throws Exception {

        int emailResult = memberService.selectMemberEmailCheck(memberInsertReq.getEmail());

        if(emailResult == 0){
            if(!memberInsertReq.getPassword().equals(memberInsertReq.getPasswordCheck())){
                throw new RestApiException(CommonErrorCode.NOT_FOUND);
            }
            memberService.insertMember2(memberInsertReq);
        }
        return new CommonResponse<>();
    }

    /**
     * 이메일 찾기
     * @param memberFindReq
     * @return
     * @throws Exception
     */
    @PostMapping("/find/email")
    public MemberEmailResponse findEmail(@RequestBody @Valid MemberFindReq memberFindReq) throws Exception{
        return memberService.findEmail(memberFindReq);
    }

    /**
     * 비밀번호 찾기 및 메일 발송
     * @param memberFindReq
     * @return
     * @throws Exception
     */
    @PostMapping("/find/password")
    public CommonResponse<String> findPassword(@RequestBody @Valid MemberFindReq memberFindReq) throws Exception {

        if(memberFindReq.getEmail()==null){
            throw new RestApiException(CommonErrorCode.INVALID_PARAMETER);
        }

        AES256 aes256 = new AES256();
        memberFindReq.setMemberName(aes256.encrypt(memberFindReq.getMemberName().trim()));
        memberFindReq.setCellphone(aes256.encrypt(memberFindReq.getCellphone().trim()));
        Integer  check = memberService.selectMemberMemberCheck(memberFindReq);
        if(check == null){
            throw  new RestApiException(CommonErrorCode.INTERNAL_SERVER_ERROR);
        }

        Mail mail = emailService.createMailAndChangePassword(memberFindReq.getEmail());
        emailService.mailSend(mail);


        return new CommonResponse<>("이메일로 임시 비밀번호가 발송되었습니다.");

//        return ResponseEntity.ok().body("이메일로 임시 비밀번호가 발송되었습니다.");
    }


    @PostMapping("/mypage")
    public boolean updateMypage(@RequestBody @Valid MemberUpdateReq memberUpdateReq, HttpServletRequest request) throws Exception {

        String email = (String) request.getAttribute("email");
        memberUpdateReq.setEmail(email);
        return  memberService.updateMypage(memberUpdateReq);

    }

}
