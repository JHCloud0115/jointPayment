package org.example.mapper.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.member.Member;
import org.example.model.response.MemberPassword;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MemberMapper {
    List<Member> selectMembers() throws DataAccessException;
    Member selectMemberByEmail(@Param(("email"))String email) throws DataAccessException;
    int selectMemberIdCheck(@Param(("email"))String email) throws DataAccessException;

    MemberPassword selectMemberPasswordByEmail(@Param("email") String email) throws DataAccessException;
    void insertMember(Member member) throws DataAccessException;

    void insertMember2(MemberInsertReq memberInsertReq) throws DataAccessException;
}
