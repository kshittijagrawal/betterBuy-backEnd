package com.example.TraningProject1Ecommerce.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MerchantProductsDto {
    private String productId;
    private String productName;
    Integer stock;
    Integer stockSold;
    Integer price;
    String image;
    String productReview;

}
