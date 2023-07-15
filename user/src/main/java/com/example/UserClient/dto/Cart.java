package com.example.UserClient.dto;

import lombok.Data;

@Data
public class Cart {
    private String productId;
    private String merchantId;
    private int itemCount;
}
