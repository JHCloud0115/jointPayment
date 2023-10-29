package org.example.service.member.impl;

import org.example.common.util.AES256;
import org.example.common.util.SHA256;
import org.example.mapper.member.MemberMapper;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.member.Member;
import org.example.model.response.MemberPasswordByEmail;
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
    public List<Member> selectMembers() throws Exception {
        return memberMapper.selectMembers();
    }
    @Override
    public int selectMemberEmailCheck(String email) throws Exception {
        return memberMapper.selectMemberIdCheck(email);
    }
    @Override
    public MemberPasswordByEmail selectMemberPasswordByEmail(String email)throws Exception{
        return memberMapper.selectMemberPasswordByEmail(email);
    }

    @Override
    public void insertMember2(MemberInsertReq memberInsertReq) throws Exception{

        if (memberInsertReq.getPassword().equals(memberInsertReq.getPasswordCheck())){
            SHA256 sha256 = new SHA256();
            String pw = sha256.encrypt(memberInsertReq.getPassword());
            String pwCheck = sha256.encrypt(memberInsertReq.getPasswordCheck());

            memberInsertReq.setPassword(pw);
            memberInsertReq.setPasswordCheck(pwCheck);
        }

        memberInsertReq.setEmail(memberInsertReq.getEmail().toLowerCase());
        AES256 aes256 = new AES256();
        memberInsertReq.setName(aes256.encrypt(memberInsertReq.getName()));
        memberInsertReq.setCellphone(aes256.encrypt(memberInsertReq.getCellphone()));

        memberMapper.insertMember2(memberInsertReq);
    }

}
