package com.example.merchant.controller;

import com.example.merchant.dto.MerchantDTO;
import com.example.merchant.dto.MerchantLoginDto;
import com.example.merchant.entity.Merchant;
import com.example.merchant.service.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://10.20.5.34:8080", "http://10.20.4.248:8080"})
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginMerchant(@RequestBody MerchantLoginDto merchantLoginDto) {

        return new ResponseEntity<>(merchantService.loginMerchant(merchantLoginDto), HttpStatus.OK);
    }

    @PostMapping("/insertorupdate")
    public ResponseEntity<Boolean> insertOrUpdateMerchant(@RequestBody MerchantDTO merchantDTO){
        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDTO,merchant);
        merchantService.insertOrUpdate(merchant);
        return new ResponseEntity(true,HttpStatus.CREATED);
    }

    @DeleteMapping("/deletemerchant/{merchantId}")
    public ResponseEntity<String> deleteMerchant(@PathVariable String merchantId){
        String status=merchantService.deleteByMerchantId(merchantId);
        return new ResponseEntity<String>(status,HttpStatus.OK);    }

    @GetMapping("/getMerchantName/{merchantId}")
    public ResponseEntity<String> getMerchantName(@PathVariable("merchantId") String merchantId) {
        return new ResponseEntity<>(merchantService.getMerchantName(merchantId), HttpStatus.OK);
    }
//    @PostMapping("/addproduct/{productId}")
//    public ResponseEntity<Product> addProduct(@PathVariable String productId){
//
//    }
}
