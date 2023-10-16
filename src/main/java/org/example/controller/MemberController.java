package org.example.controller;

import org.example.model.CommonResponse;
import org.example.model.member.Member;
import org.example.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController("/member")
public class MemberController {

//    @Autowired
//    public MemberController() {
//    }
private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping
    public CommonResponse<Void> selectMember() throws Exception {
        return new CommonResponse<>();
    }

    @GetMapping("/All")
    public List<Member> selectMembers() throws Exception{
        return memberService.selectMembers();
    }

    @PostMapping("/new")
    public void insertMember(Member member) throws Exception{
        memberService.insertMember(member);
    }


}
