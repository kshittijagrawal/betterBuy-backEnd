package com.example.UserClient.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private String productId;
    private String productName;
    private String brand;
    private String category;
    private String description;
    private String productUsp;
    private List<MerchantInventoryDto> merchants;
}
