package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.member.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.Mapping;


@Mapper
public interface MemberMapper {
    Member selectMemberById(@Param("memberId") String memberId) throws DataAccessException;
    void insertMember(Member member) throws DataAccessException;
}
