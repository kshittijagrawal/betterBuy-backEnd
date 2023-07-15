package com.example.UserClient.feignClient;

import com.example.UserClient.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "Product-Service",url = "http://localhost:8082/product")

public interface APIClient {

    @DeleteMapping(value = "/delProduct/{productId}/{merchantId}")
    Boolean deleteProductById(@PathVariable("productId") String productId, @PathVariable("merchantId") String merchantId);
//
//    @GetMapping("/findOne/{productId}")
//    ProductDTO findOne(@PathVariable("productId") String productId);

    @GetMapping("/find")
    ProductDTO findAll();

    @PostMapping("/addMerchantProduct")
    String addMerchantProduct(@RequestBody ProductDTO productDTO);

    @PutMapping("/editmerchantproduct/{productId}")
    Validation editMerchantProduct(@PathVariable("productId") String productId, @RequestBody MerchantInventoryDto merchantInventoryDto);

    @GetMapping("/getItemCount/{productId}/{merchantId}")
    Integer getItemCount(@PathVariable("productId") String productId, @PathVariable("merchantId") String merchantId);

    @GetMapping("/findOneProduct/{productId}")
    ProductDTO findById(@PathVariable("productId") String productId);

    @GetMapping("/getProductDetailsCart/{productId}/{merchantId}")
    ProductResponse findProductDetailsCart(@PathVariable("productId") String productId, @PathVariable("merchantId") String merchantId);
}