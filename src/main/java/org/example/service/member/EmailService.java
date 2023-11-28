package org.example.service.member;

import org.example.model.member.Mail;

public interface EmailService {
    Mail createMailAndChangePassword(String email) throws Exception;
    void mailSend(Mail mail) throws Exception;
}
