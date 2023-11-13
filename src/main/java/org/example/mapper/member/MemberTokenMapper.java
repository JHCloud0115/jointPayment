package org.example.mapper.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.member.Member;
import org.example.model.member.MemberToken;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.req.member.MemberTokenReq;
import org.example.model.response.member.MemberLoginFailResp;
import org.example.model.response.member.MemberPassword;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MemberTokenMapper {

    MemberToken selectMemberTokenByEmail(@Param(("email"))String email) throws Exception;
    void upateMemberToken(MemberTokenReq memberTokenReq) throws Exception;
    void expireRefreshToken(@Param(("email"))String email) throws Exception;
    MemberToken insertMemberToken(MemberToken memberToken) throws Exception;

}
