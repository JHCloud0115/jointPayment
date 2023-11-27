package org.example.service.member;

import org.apache.ibatis.annotations.Param;
import org.example.model.member.Mail;
import org.example.service.member.MemberLoginService;

public interface EmailService {
    Mail createMailAndChangePassword(String email) throws Exception;
    void updatePassword(String email, String password) throws Exception;
    void mailSend(Mail mail) throws Exception;
}
