package org.example.mapper.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.member.Member;
import org.example.model.member.MemberToken;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.response.member.MemberLoginFailResp;
import org.example.model.response.member.MemberPassword;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MemberTokenMapper {
    MemberToken insertMemberToken(MemberToken memberToken) throws Exception;
}
