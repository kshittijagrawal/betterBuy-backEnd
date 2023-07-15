package com.example.merchant.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document(collection = Merchant.COLLECTION_NAME)

public class Merchant {

    public static final String COLLECTION_NAME="Merchant";

    private String  merchantName;

    private String merchantAddress ;
    @Id
    private String merchantEmail;
    private String merchantContactNo;

    private Integer merchantRating=0;
    private String merchantUserName;
    private String merchantPassword;



}
