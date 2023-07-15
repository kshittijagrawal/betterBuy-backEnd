package com.example.merchant.dto;

import lombok.Data;

@Data
public class MerchantDTO {
    private String merchantEmail;
    private String  merchantName;

    private String merchantAddress ;

    private Long merchantContactNo;

    private Integer merchantRating=0;

    private String merchantUserName;
    private String merchantPassword;
}
