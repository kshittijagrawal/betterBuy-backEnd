package com.example.UserClient.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String emailId;
    String password;
}
