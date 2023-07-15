package com.example.TraningProject1Ecommerce.dtos;

import lombok.Data;

import java.util.List;


@Data
public class MerchantInventoryDto {

    String merchantId;
    Integer stock;
    Integer stockSold;
    Integer price;
    String productReview;
    String image;

}
