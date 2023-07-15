package com.example.UserClient.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "Merchant-Service",url = "http://localhost:8081/merchant")
public interface MerchantClient {
    @GetMapping("/getMerchantName/{merchantId}")
    String getMerchantName(@PathVariable("merchantId") String merchantId);
}
