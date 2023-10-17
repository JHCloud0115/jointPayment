package org.example.service;

import org.example.model.MemberInsertReq;
import org.example.model.member.Member;

import java.util.List;

public interface MemberService {

    List<Member> selectMembers() throws Exception;

    Member selectMemberById(String memberId) throws Exception;

    void insertMember(Member member) throws Exception;

    void insertMember2(MemberInsertReq memberInsertReq) throws Exception;

}
