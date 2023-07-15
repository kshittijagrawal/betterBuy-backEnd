package com.example.UserClient.entity;

import com.example.UserClient.dto.Cart;
import com.example.UserClient.dto.History;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document()
public class User {
    @Id
    private String emailId;
    private String firstName;
    private String lastName;
    private String password;
    private String dob;
    private String address;
    private long mobile;
    private List<Cart> cartList=new ArrayList<Cart>();
    private List<History> historyList=new ArrayList<History>();
}
