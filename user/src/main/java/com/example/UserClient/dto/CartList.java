package com.example.UserClient.dto;


import lombok.Data;

@Data
public class CartList {
    String image;
    String productName;
    String merchantName;
    int itemCount;
    String price;
    String productId;
    String merchantId;
}
