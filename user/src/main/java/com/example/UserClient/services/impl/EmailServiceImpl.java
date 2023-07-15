package com.example.UserClient.services.impl;

import com.example.UserClient.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender javaEmailSender;
    @Override
    public void sendMail(String to, String Subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(Subject);
        mail.setText(text);
        javaEmailSender.send(mail);
    }
}
