package org.example.service;

import org.example.model.member.Member;

public interface MemberService {

    Member selectMemberById(String memberId) throws Exception;

}
