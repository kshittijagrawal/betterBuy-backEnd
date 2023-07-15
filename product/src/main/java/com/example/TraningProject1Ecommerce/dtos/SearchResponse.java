package com.example.TraningProject1Ecommerce.dtos;

import lombok.Data;

@Data
public class SearchResponse {
    private String productId;
    private String productName;
    private String brand;
    private String category;
    private  String description;
    private String image;
    private String productUsp;
    private String merchantId;
    private Integer stock;
    private Integer stockSold;
    private Integer price;
    private String productReview;
    private Integer merchantCount;
}
