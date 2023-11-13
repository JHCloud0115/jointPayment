package org.example.mapper.member;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.response.member.MemberLoginFailResp;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface MemberLoginFailMapper {
    MemberLoginFailResp selectMemberLoginFailCnt(String email) throws Exception;
}
