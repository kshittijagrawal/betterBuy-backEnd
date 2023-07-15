package com.example.merchant.service.impl;

import com.example.merchant.dto.MerchantLoginDto;
import com.example.merchant.entity.Merchant;
import com.example.merchant.repository.MerchantRepository;
import com.example.merchant.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;

@Service
class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    MerchantService merchantService;

    public Merchant insertOrUpdate(Merchant merchant){

            return merchantRepository.insert(merchant);


    }
    public String  deleteByMerchantId(String merchantId){
        if(merchantRepository.existsById(merchantId)){
            merchantRepository.deleteById(merchantId);
            return "Deleted Successfully";
        }
        return "Unsuccessful deletion";
    }
    public Boolean loginMerchant(MerchantLoginDto merchantLoginDto) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantLoginDto.getMerchantId());
        if(merchant.isPresent()) {
            if (merchant.get().getMerchantPassword().equals(merchantLoginDto.getMerchantPass())) {
                return true;
            }
        }
        return false;
    }


    public String getMerchantName(String merchantId) {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if(merchant.isPresent()) {
            return merchant.get().getMerchantName();
        }

        return null;

    }



}
