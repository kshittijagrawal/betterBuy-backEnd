package com.example.TraningProject1Ecommerce.dtos;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import java.util.UUID;


@Data
public class ProductDto {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String productId;
    private String productName;
    private String brand;
    private String category;
    private String description;
    private String productUsp;
    private List<MerchantInventoryDto> merchants;
}
