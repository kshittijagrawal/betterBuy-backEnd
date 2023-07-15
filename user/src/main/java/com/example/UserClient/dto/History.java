package com.example.UserClient.dto;

import lombok.Data;

@Data
public class History {
    private String dateOfPurchase;
    private String merchantId;
    private String productId;
    String image;
    String productName;
    String merchantName;
    int itemCount;
    String price;
    private int numberOfItems;
}
