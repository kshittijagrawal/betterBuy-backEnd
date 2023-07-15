//package com.example.TraningProject1Ecommerce.controller;
//
//
////import com.example.TraningProject1Ecommerce.entity.MerchantProduct;
//import com.example.TraningProject1Ecommerce.dtos.MerchantInventoryDto;
//import com.example.TraningProject1Ecommerce.dtos.ProductDto;
//import com.example.TraningProject1Ecommerce.entity.Product;
//import com.example.TraningProject1Ecommerce.repository.ProductRepository;
////import com.example.TraningProject1Ecommerce.services.MerchantProductService;
//import com.example.TraningProject1Ecommerce.services.ProductService;
//import com.sun.org.apache.xpath.internal.operations.Bool;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.xml.ws.Response;
//import java.util.List;
//
//@RestController
//@RequestMapping("/product")
//public class ProductController {
//
//    @Autowired
//    ProductService productService;
//
////    @Autowired
////    MerchantProductService merchantProductService;
//@PostMapping("/addOrUpdate")
//public ResponseEntity<Product> editOrInsertProduct(@RequestBody ProductDto productDto){
////        Product product=new Product();
////        BeanUtils.copyProperties(productDto,product);
//    productService.editOrInsertProduct(productDto);
//    return new ResponseEntity(productDto,HttpStatus.CREATED);
//}
//
//    @DeleteMapping("/delProduct/{productId}/{merchantId}")
//    public ResponseEntity<Boolean> deleteProductById(@PathVariable("productId") String productId, @PathVariable String merchantId){
//
//            return new ResponseEntity(productService.removeMerchantProduct(productId, merchantId),HttpStatus.OK);
////        return new ResponseEntity(Boolean.FALSE,HttpStatus.OK);
//    }
//
//    @GetMapping("/findOne/{productId}")
//    public ResponseEntity<Product> findOne(@PathVariable String productId){
//        return new ResponseEntity(productService.findById(productId),HttpStatus.OK);
//    }
//    @GetMapping("/find")
//    public ResponseEntity<Product> findAll(){
//        return new ResponseEntity(productService.findAllProducts(),HttpStatus.OK);
//    }
//    @PostMapping("/addMerchantProduct/{productId}")
//    public ResponseEntity<Boolean> addMerchantProduct(@PathVariable("productId")String productId, @RequestBody MerchantInventoryDto merchantInventoryDto){
//        return new ResponseEntity<Boolean>(productService.addMerchantProduct(productId, merchantInventoryDto), HttpStatus.OK);
//    }
//
//    @PutMapping("/editmerchantproduct/{productId}")
//    public ResponseEntity<Boolean> editMerchantProduct(@PathVariable("productId") String productId, @RequestBody MerchantInventoryDto merchantInventoryDto) {
//        return new ResponseEntity<>(productService.editMerchantProduct(productId, merchantInventoryDto), HttpStatus.OK);
//    }
//    @GetMapping("/getItemCount/{productId}/{merchantId}")
//    public ResponseEntity<Integer> getItemCount(@PathVariable("productId") String productId,@PathVariable("merchantId") String merchantId){
//        return new ResponseEntity<Integer>(productService.getItemCount(productId,merchantId),HttpStatus.OK);
//    }
//
//
//}
package com.example.TraningProject1Ecommerce.controller;


//import com.example.TraningProject1Ecommerce.entity.MerchantProduct;
import com.example.TraningProject1Ecommerce.dtos.*;
import com.example.TraningProject1Ecommerce.entity.Product;
import com.example.TraningProject1Ecommerce.repository.ProductRepository;
//import com.example.TraningProject1Ecommerce.services.MerchantProductService;
import com.example.TraningProject1Ecommerce.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8083")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

//    @Autowired
//    MerchantProductService merchantProductService;

    @PostMapping("/addOrUpdate")
    public ResponseEntity<Product> editOrInsertProduct(@RequestBody ProductDto productDto){
//        Product product=new Product();
//        BeanUtils.copyProperties(productDto,product);
        productService.editOrInsertProduct(productDto);
        return new ResponseEntity(productDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/delProduct/{productId}/{merchantId}")
    public ResponseEntity<ValidationDto> deleteProductById(@PathVariable("productId") String productId, @PathVariable String merchantId){
        ValidationDto validationDto = new ValidationDto();
        validationDto.setValid(productService.removeMerchantProduct(productId, merchantId));
        return new ResponseEntity(validationDto,HttpStatus.OK);
//        return new ResponseEntity(Boolean.FALSE,HttpStatus.OK);
    }

    @GetMapping("/findOne/{productId}")
    public ResponseEntity<Product> findOne(@PathVariable String productId){
        return new ResponseEntity(productService.findById(productId),HttpStatus.OK);
    }
    @GetMapping("/find")
    public ResponseEntity<Product> findAll(){
        return new ResponseEntity(productService.findAllProducts(),HttpStatus.OK);
    }
    @PostMapping("/addmerchantproduct/{productId}")
    public ResponseEntity<ValidationDto> addMerchantProduct(@PathVariable("productId") String productId, @RequestBody MerchantInventoryDto merchantInventoryDto){
        ValidationDto validationDto = new ValidationDto();
        validationDto.setValid(productService.addMerchantProduct(productId, merchantInventoryDto));
        System.out.println(productId + merchantInventoryDto.toString());
        return new ResponseEntity<>(validationDto, HttpStatus.OK);
    }

    @PutMapping("/editmerchantproduct/{productId}")
    public ResponseEntity<ValidationDto> editMerchantProduct(@PathVariable("productId") String productID, @RequestBody MerchantInventoryDto merchantInventoryDto) {
        ValidationDto validationDto = new ValidationDto();
        validationDto.setValid(productService.editMerchantProduct(productID, merchantInventoryDto));
        return new ResponseEntity<>(validationDto, HttpStatus.OK);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<SearchResultsList> getProducts() {
        List<Product> productList=productService.findAllProducts();
        List<SearchResponse> searchResponseList=new ArrayList<SearchResponse>();
        for(int i=0;i<productList.size();i++){
            for(int j=0;j<productList.get(i).getMerchants().size();j++){
                SearchResponse searchResponse=new SearchResponse();
                searchResponse.setMerchantCount(productList.get(i).getMerchants().size());
                BeanUtils.copyProperties(productList.get(i).getMerchants().get(j),searchResponse);
                BeanUtils.copyProperties(productList.get(i),searchResponse);
                searchResponseList.add(searchResponse);
            }
        }
        SearchResultsList searchResultsList=new SearchResultsList();
        searchResultsList.setSearchResponses(searchResponseList);
        return new ResponseEntity<>(searchResultsList, HttpStatus.OK);
    }

}