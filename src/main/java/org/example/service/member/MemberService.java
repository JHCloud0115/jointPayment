package org.example.service.member;

import org.example.model.req.member.MemberInsertReq;
import org.example.model.member.Member;
import org.example.model.response.MemberPasswordByEmail;

import java.util.List;

public interface MemberService {

    List<Member> selectMembers() throws Exception;

    int selectMemberEmailCheck(String email) throws Exception;

    MemberPasswordByEmail selectMemberPasswordByEmail(String email) throws Exception;
    void insertMember2(MemberInsertReq memberInsertReq) throws Exception;

}
