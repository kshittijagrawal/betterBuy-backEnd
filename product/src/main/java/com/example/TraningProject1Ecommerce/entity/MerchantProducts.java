package com.example.TraningProject1Ecommerce.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "MerchantProducts")
public class MerchantProducts {
    @Id
    private String merchantId;
    private List<String> products;
}
