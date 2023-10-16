package org.example.service.impl;

import org.example.mapper.MemberMapper;
import org.example.model.member.Member;
import org.example.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;


    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    @Override
    public Member selectMemberById(String memberId) throws Exception {
        return memberMapper.selectMemberById(memberId);
    }

    @Override
    public List<Member> selectMembers() throws Exception {
        return memberMapper.selectMembers();
    }

    @Override
    public void insertMember(Member member) throws Exception{
        Member validateMember = new Member();

        if (member.getEmail().equals(validateMember.getEmail())){
            throw new IllegalStateException("이미 가입된 회원입니다");
        }
        memberMapper.insertMember(member);
    }

}
