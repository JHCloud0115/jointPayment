package org.example.service.member.impl;

import org.example.common.util.PasswordGenerator;
import org.example.common.util.SHA256;
import org.example.mapper.member.MemberMapper;
import org.example.model.member.Mail;
import org.example.service.member.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private PasswordGenerator passwordGenerator;
    private MemberMapper memberMapper;
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


    @Autowired
    public EmailServiceImpl(
            PasswordGenerator passwordGenerator,
            MemberMapper memberMapper,
            JavaMailSender javaMailSender
    ){
        this.passwordGenerator =passwordGenerator;
        this.memberMapper = memberMapper;
        this.javaMailSender = javaMailSender;
    }


    //메일 내용 생성하고 임시비밀번호 발급 후 업데이트 진행
    @Override
    public Mail createMailAndChangePassword(String email) throws Exception{
        String password = passwordGenerator.generateRandomPassword();
        Mail mail = new Mail();
        mail.setAddress(email);
        mail.setTitle("임시 비밀번호 안내 이메일 입니다.");
        mail.setMessage("안녕하세요. 임시비밀번호 안내 관련 이메일입니다."+"회원님의 임시 비밀번호는"
            +password+"입니다."+"로그인 후 비밀번호를 변경해주세요");

        SHA256 sha256 = new SHA256();
        password = sha256.encrypt(password);
        memberMapper.updateMemberPassword(email,password);
        return mail;
    }



    //메일 발송
    @Override
    public void mailSend(Mail mail) throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        message.setFrom(fromEmail);
        javaMailSender.send(message);
    }



}
