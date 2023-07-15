package com.example.merchant.service;

import com.example.merchant.dto.MerchantLoginDto;
import com.example.merchant.entity.Merchant;

public interface MerchantService {
    public Merchant insertOrUpdate(Merchant merchant);
    public String deleteByMerchantId(String merchantId);
    Boolean loginMerchant(MerchantLoginDto merchantLoginDto);

    String getMerchantName(String merchantId);
}


