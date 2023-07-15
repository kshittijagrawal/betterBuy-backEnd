package com.example.TraningProject1Ecommerce.repository;


import com.example.TraningProject1Ecommerce.entity.MerchantProducts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantProductsRepository extends MongoRepository<MerchantProducts, String> {
}
