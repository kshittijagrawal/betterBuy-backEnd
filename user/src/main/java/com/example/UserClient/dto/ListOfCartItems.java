package com.example.UserClient.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListOfCartItems {
    private List<CartList> cartList;
}
