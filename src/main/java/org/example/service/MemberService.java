package org.example.service;

import org.example.mapper.MemberMapper;
import org.example.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private final MemberMapper memberMapper;

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


    public Member selectMemberById(String memberId){
        return memberMapper.selectMemberById(memberId);

    }


}
