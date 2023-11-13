package org.example.mapper.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.member.LoginFail;
import org.example.model.response.member.MemberLoginFailResp;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface MemberLoginFailMapper {
    MemberLoginFailResp selectMemberLoginFailCnt(@Param("email") String email) throws Exception;
    void insertLoginFail(LoginFail fail) throws DataAccessException;
    void updateLoginFailCount(LoginFail fail) throws DataAccessException;
}
