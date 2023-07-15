package com.example.TraningProject1Ecommerce.services.implementation;

//import com.example.TraningProject1Ecommerce.entity.MerchantProduct;
import com.example.TraningProject1Ecommerce.dtos.MerchantInventoryDto;
import com.example.TraningProject1Ecommerce.dtos.ProductDto;
import com.example.TraningProject1Ecommerce.entity.MerchantProducts;
import com.example.TraningProject1Ecommerce.entity.Product;
import com.example.TraningProject1Ecommerce.repository.MerchantProductsRepository;
import com.example.TraningProject1Ecommerce.repository.ProductRepository;
import com.example.TraningProject1Ecommerce.services.ProductService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepository productRepository;

//    @Autowired
//    MongoTemplate mongoTemplate;
    @Autowired
    MerchantProductsRepository merchantProductsRepository;
//    @Autowired
//    MerchantProduct merchantProduct;
    @Override
    public Product findById(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            return product.get();
        }
        return null;

    }
    @Override
    public Product editOrInsertProduct(ProductDto productDto) {
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        if(productRepository.existsById(productDto.getProductId())){

            productRepository.save(product);
            return product;
        }
        System.out.println("product not found with given id...");
        productRepository.insert(product);
        return product;
    }
    @Override
    public Boolean existsById(String productId) {
        if(productRepository.existsById(productId)){
            return Boolean.TRUE;
        }
        System.out.println("NO such product with given Id...");
        return Boolean.FALSE;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
//        return null;
    }

    @Override
    public List<Product> findByCategory(String category) {

        return null;
    }

    @Override
    public List<MerchantInventoryDto> findAllMerchantsOfProduct(String productId) {
//        List<MerchantInventoryDto> merchants=new ArrayList<>();
//        for(MerchantInventoryDto m:productRepository.findById(productId).get().getMerchants()){
//
//        }
        if(productRepository.findById(productId).isPresent())
            return productRepository.findById(productId).get().getMerchants();
        System.out.println("NO such product with given id ...");
        return null;
        }

//    @Override
//    public Boolean addProduct(Product product) {
//        productRepository.insert(product);
////        productRepository.save(product);
//        return Boolean.TRUE;
//    }

//    @Override
//    public String addMerchantProduct(ProductDto productDto) {
//        Boolean existsByBrand=productRepository.existsByBrand(productDto.getBrand());
//        Boolean existsByProductName=productRepository.existsByProductName(productDto.getProductName());
//        Boolean existsByCategory=productRepository.existsByCategory(productDto.getCategory());
//        if(existsByBrand && existsByCategory && existsByProductName){
//            Query query = new Query();
//            Update update = new Update();
//            query.addCriteria((Criteria.where("productName").is(productDto.getProductName())));
//            update.addToSet("merchantList",productDto.getMerchants().get(0));
//            mongoTemplate.findAndModify(query,update,FindAndModifyOptions.options().upsert(true),Product.class);
//            return "added to an existing product";
//        }
//        else{
//            Product product=new Product();
//            BeanUtils.copyProperties(productDto,product);
//            productRepository.insert(product);
//            return "added to an new product";
//        }
//    }
@Override
public Boolean addMerchantProduct(String productId, MerchantInventoryDto merchantInventoryDto) {
    Optional<Product> product = productRepository.findById(productId);
    String merchantId = merchantInventoryDto.getMerchantId();
    Optional<MerchantProducts> merchantProducts = merchantProductsRepository.findById(merchantId);
    if(merchantProducts.isPresent()) {
        List<String> productIds = merchantProducts.get().getProducts();
        if(!productIds.contains(productId)) {
            productIds.add(productId);
        }
        merchantProducts.get().setProducts(productIds);
        merchantProductsRepository.save(merchantProducts.get());
    }
    else {
//            merchantProducts.get().setMerchantId(merchantInventoryDto.getMerchantId());
        MerchantProducts merchantProducts1 = new MerchantProducts();
        merchantProducts1.setMerchantId(merchantInventoryDto.getMerchantId());
        List<String> merchantP = new ArrayList<>();
        merchantP.add(productId);
        merchantProducts1.setProducts(merchantP);
        merchantProductsRepository.save(merchantProducts1);
    }
    if(product.isPresent()) {
        List<MerchantInventoryDto> merchantList = product.get().getMerchants();
        merchantList.add(merchantInventoryDto);
        product.get().setMerchants(merchantList);
        productRepository.save(product.get());
        return true;
    }
    return false;
}

    @Override
    public Boolean editMerchantProduct(String productId, MerchantInventoryDto merchantInventoryDto ) {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            Product product1=product.get();
            List<MerchantInventoryDto> merchantList = product1.getMerchants();
            for (int i = 0; i < merchantList.size(); i++) {
                if (merchantList.get(i).getMerchantId().equals(merchantInventoryDto.getMerchantId())) {
                    merchantList.remove(i);
                    merchantList.add(merchantInventoryDto);
                    product1.setMerchants(merchantList);
                    productRepository.save(product1);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int getItemCount(String productId, String merchantId) {
        List<MerchantInventoryDto> merchants=productRepository.findById(productId).get().getMerchants();
        for(int i=0;i<merchants.size();i++){
            if(merchants.get(i).getMerchantId().equals(merchantId)){
                return merchants.get(i).getStock();
            }
        }
        return -1;
    }

    @Override
    public Boolean removeMerchantProduct(String productId, String merchantId) {
//        if(productRepository.existsById(productId)){
//            productRepository.deleteById(productId);
//            return Boolean.TRUE;
//        }
//        System.out.println("NO such product with given Id...");
//        return Boolean.FALSE;

        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()) {
            List<MerchantInventoryDto> merchantList = product.get().getMerchants();
            for(int i = 0; i < merchantList.size(); i++) {
                if(merchantList.get(i).getMerchantId().equals(merchantId)){
                    merchantList.remove(i);
                    product.get().setMerchants(merchantList);
                    productRepository.save(product.get());
                    return true;
                }
            }
            return false;
        }
        else {
            return false;
        }
    }
//    @Override
//    public Boolean addMerchantProduct(String productId, MerchantInventoryDto merchantInventoryDto) {
//        Optional<Product> product = productRepository.findById(productId);
//        product.get().
//    }

//    @Override
//    public Product editOrInsertProduct(ProductDto productDto) {
//        Product product=new Product();
//        BeanUtils.copyProperties(productDto,product);
//        if(productRepository.existsById(productDto.getProductId())){
//
//            productRepository.save(product);
//            return product;
//        }
//        System.out.println("product not found with given id...");
//        productRepository.insert(product);
//        return product;
//    }
//        return null;
//    }
}
