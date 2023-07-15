package com.example.TraningProject1Ecommerce.entity;


import com.example.TraningProject1Ecommerce.dtos.MerchantInventoryDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Product")

public class Product {

    @Id
    private String productId;
    private String productName;
    private String brand;
    private String category;
    private  String description;
    private String productUsp;
    private List<MerchantInventoryDto> merchants;

//    private String
}
