package org.example.service.member;

import org.example.model.req.member.MemberInsertReq;
import org.example.model.member.Member;
import org.example.model.response.member.MemberLoginFailResp;
import org.example.model.response.member.MemberPassword;

import java.util.List;

public interface MemberService {

    List<Member> selectMembers() throws Exception;

    Member selectMemberByEmail(String email) throws Exception;

    int selectMemberEmailCheck(String email) throws Exception;

    MemberPassword selectMemberPasswordByEmail(String email) throws Exception;

    void insertMember2(MemberInsertReq memberInsertReq) throws Exception;

}
