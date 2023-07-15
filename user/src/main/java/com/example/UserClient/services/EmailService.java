package com.example.UserClient.services;

public interface EmailService {
    public void sendMail(String to, String Subject, String text);
}
