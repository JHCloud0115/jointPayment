package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.MemberInsertReq;
import org.example.model.member.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;


@Mapper
@Repository
public interface MemberMapper {
    List<Member> selectMembers() throws DataAccessException;
    Member selectMemberById(@Param("memberId") String memberId) throws DataAccessException;
    void insertMember(Member member) throws DataAccessException;

    void insertMember2(MemberInsertReq memberInsertReq) throws DataAccessException;
}
