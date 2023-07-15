package com.example.TraningProject1Ecommerce.services;

//import com.example.TraningProject1Ecommerce.entity.MerchantProduct;
import com.example.TraningProject1Ecommerce.dtos.MerchantInventoryDto;
import com.example.TraningProject1Ecommerce.dtos.ProductDto;
import com.example.TraningProject1Ecommerce.entity.Product;
import com.example.TraningProject1Ecommerce.repository.ProductRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product editOrInsertProduct(ProductDto productDto);

    Product findById(String productId);
    Boolean existsById(String productId);
    List<Product> findAllProducts();
    List<Product> findByCategory(String category);
    List<MerchantInventoryDto> findAllMerchantsOfProduct(String productId);
//    Boolean addProduct(Product product);
    Boolean removeMerchantProduct(String productId, String merchantId);
//    Product editOrInsertProduct(ProductDto productDto);
    Boolean addMerchantProduct(String productId, MerchantInventoryDto merchantInventoryDto);
    Boolean editMerchantProduct(String productId, MerchantInventoryDto merchantInventoryDto);
    public int getItemCount(String productId,String merchantId);
}
