package org.example.mapper.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.member.Member;
import org.example.model.req.member.MemberInsertReq;
import org.example.model.response.member.MemberPassword;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MemberLoginMapper {
    void updateMemberBlock(@Param("memberUid") int memberUid) throws DataAccessException;

}
