package org.example.mapper;

import org.example.model.Member;
import org.springframework.beans.factory.annotation.Value;

public interface MemberMapper {
    Member selectMemberById(@Value("memberId") String memberId);
    void insertMember(Member member);
}
