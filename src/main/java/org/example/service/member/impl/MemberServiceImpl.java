package org.example.service.member.impl;

import org.example.mapper.member.MemberMapper;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.member.Member;
import org.example.service.member.MemberService;
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

    public int selectMemberEmailCheck(String memberId) throws Exception {
        return memberMapper.selectMemberIdCheck(memberId);
    }

    @Override
    public void insertMember2(MemberInsertReq memberInsertReq) throws Exception{
        memberMapper.insertMember2(memberInsertReq);
    }

}
