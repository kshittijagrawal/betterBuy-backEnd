package com.example.UserClient.dto;

import lombok.Data;

@Data
public class ProductResponse {
    String image;
    String productName;
    String merchantName;
    String price;
}
