package com.example.UserClient.services.impl;

import com.example.UserClient.dto.*;
import com.example.UserClient.entity.User;
import com.example.UserClient.feignClient.APIClient;
import com.example.UserClient.feignClient.MerchantClient;
import com.example.UserClient.repository.UserRepository;
import com.example.UserClient.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    APIClient apiClient;

    @Autowired
    MerchantClient merchantClient;

    @Autowired
    UserRepository userRepository;

    @Override
    public Validation loginValidation(String userId, String userPassword) {
        Validation isValid = new Validation();
        Object obj;
        if(userRepository.existsById(userId)){
            String pass=userRepository.findById(userId).get().getPassword();
            if(userRepository.findById(userId).get().getPassword().equals(userPassword)){
                isValid.setIsValid(true);
                return isValid;
            }
            isValid.setIsValid(false);
            return isValid;
        }
        isValid.setIsValid(false);
        return isValid;
    }

    @Override
    public Validation addUser(User user) {
        Validation valid = new Validation();
        if(userRepository.existsById(user.getEmailId())) {
            valid.setIsValid(false);
            return valid;
        }
        userRepository.save(user);
        valid.setIsValid(true);
        return valid;


    }

    @Override
    public Validation removeFromCart(Cart cart, String userId) {
        Validation valid = new Validation();
        User user =userRepository.findById(userId).get();
        List<Cart> cartList=user.getCartList();
        for(int i=0;i<user.getCartList().size();i++) {
            if (cartList.get(i).getProductId().equals(cart.getProductId()) && cartList.get(i).getMerchantId().equals(cart.getMerchantId())) {
                Cart cartUpdated=new Cart();
                cartUpdated.setItemCount(cartList.get(i).getItemCount()- cart.getItemCount());
                cartUpdated.setMerchantId(cart.getMerchantId());
                cartUpdated.setProductId(cart.getProductId());
                cartList.remove(i);
                cartList.add(cartUpdated);
                userRepository.save(user).getCartList();
                valid.setIsValid(true);
                return valid;
            }
        }
        return null;
    }

    @Override
    public Validation addToCart(Cart cart, String userId) {
//        ProductDTO productDTO = apiClient.findById(cart.getProductId());
//        List<MerchantInventoryDto> merchantInventoryDtos = productDTO.getMerchants();
//        for(int i = 0; i < merchantInventoryDtos.size(); i++) {
//            if(merchantInventoryDtos.get(i).getMerchantId().equals(cart.getMerchantId())) {
//                merchantInventoryDtos.get(i).setStock(merchantInventoryDtos.get(i).getStock()-1);
//            }
//        }
        Validation valid = new Validation();
        User user =userRepository.findById(userId).get();
        List<Cart> cartList=user.getCartList();
        for(int i=0;i<user.getCartList().size();i++) {
            if (cartList.get(i).getProductId().equals(cart.getProductId()) && cartList.get(i).getMerchantId().equals(cart.getMerchantId())) {
                Cart cartUpdated=new Cart();
                cartUpdated.setItemCount(cartList.get(i).getItemCount()+cart.getItemCount());
                cartUpdated.setMerchantId(cart.getMerchantId());
                cartUpdated.setProductId(cart.getProductId());
                cartList.remove(i);
                cartList.add(cartUpdated);
                userRepository.save(user).getCartList();
                valid.setIsValid(true);
                return valid;
            }
        }
        cartList.add(cart);
        user.setCartList(cartList);
        userRepository.save(user).getCartList();
        valid.setIsValid(true);
        return valid;
    }

    @Override
    public Validation updateCartItem(Cart cart, String userId) {
        Validation valid = new Validation();
        User user =userRepository.findById(userId).get();
        List<Cart> cartList=user.getCartList();
        for(int i=0;i<user.getCartList().size();i++){
            if(cartList.get(i).getProductId().equals(cart.getProductId()) &&cartList.get(i).getMerchantId().equals(cart.getMerchantId())){
                cartList.remove(i);
                cartList.add(cart);
                user.setCartList(cartList);
                break;
            }
        }
        userRepository.save(user).getCartList();
        valid.setIsValid(true);
        return valid;

    }

    @Override
    public Validation deleteFromCart(String userId, String productId, String merchantId) {
        Validation valid = new Validation();
        User user=userRepository.findById(userId).get();
        List<Cart> cartList=user.getCartList();
        for(int i=0;i<cartList.size();i++){
            if(cartList.get(i).getProductId().equals(productId) &&cartList.get(i).getMerchantId().equals(merchantId)){
                cartList.remove(i);
                user.setCartList(cartList);
                valid.setIsValid(true);
                userRepository.save(user).getCartList();
                return valid;
                //return "deleted from cart";
            }
        }
        valid.setIsValid(false);
        return valid;
    }


    @Override
    public List<History> viewHistory(String userId) {
        Optional<User> user = userRepository.findById(userId);
        List<History> histories = user.get().getHistoryList();
        List<History> histories1 = new ArrayList<>();
        for(int i = 0; i < histories.size(); i++) {
            History history = new History();

            ProductResponse productResponse = new ProductResponse();
            BeanUtils.copyProperties(apiClient.findProductDetailsCart(histories.get(i).getProductId(), histories.get(i).getMerchantId()), productResponse);
            history.setImage(productResponse.getImage());
            history.setMerchantName(merchantClient.getMerchantName(histories.get(i).getMerchantId()));
            history.setProductName(productResponse.getProductName());
            history.setPrice(productResponse.getPrice());
            history.setMerchantId(histories.get(i).getMerchantId());
            history.setProductId(histories.get(i).getProductId());
            history.setItemCount(histories.get(i).getItemCount());
            history.setDateOfPurchase(histories.get(i).getDateOfPurchase());
            histories1.add(history);
        }
        return histories1;
    }
    @Override
    public List<CartList> viewCart(String userId) {
        Optional<User> user = userRepository.findById(userId);
        List<CartList> cartResponses = new ArrayList<>();

        List<Cart> carts = user.get().getCartList();
        for(int i = 0; i < carts.size(); i++) {
            ProductResponse productResponse = new ProductResponse();
            CartList cartResponse = new CartList();
            BeanUtils.copyProperties(apiClient.findProductDetailsCart(carts.get(i).getProductId(), carts.get(i).getMerchantId()), productResponse);
            cartResponse.setImage(productResponse.getImage());
            cartResponse.setMerchantName(merchantClient.getMerchantName(carts.get(i).getMerchantId()));
            cartResponse.setProductName(productResponse.getProductName());
            cartResponse.setPrice(productResponse.getPrice());
            cartResponse.setMerchantId(carts.get(i).getMerchantId());
            cartResponse.setProductId(carts.get(i).getProductId());
            cartResponse.setItemCount(carts.get(i).getItemCount());
            cartResponses.add(cartResponse);

        }
        return cartResponses;
//        userRepository.findById(userId).get().getCartList();
    }

    @Override
    public Validation removeCart(String userId) {
        Optional<User> user = userRepository.findById(userId);
        Validation valid = new Validation();
        if(user.isPresent()) {
            user.get().setCartList(new ArrayList<>());
            userRepository.save(user.get());
            valid.setIsValid(true);
            return valid;
        }
        valid.setIsValid(false);
        return valid;

    }

    @Override
    public Validation checkOut( String userId) {
        Optional<User> user1 = userRepository.findById(userId);
        List<Cart> carts = user1.get().getCartList();
        MerchantInventoryDto change;
        Validation valid = new Validation();
        String id;
        for(int i = 0; i < carts.size(); i++) {
            ProductDTO productDTO = apiClient.findById(carts.get(i).getProductId());
            List<MerchantInventoryDto> merchantInventoryDtos = productDTO.getMerchants();
            for(int j = 0; j < merchantInventoryDtos.size(); j++) {
                if(merchantInventoryDtos.get(j).getMerchantId().equals(carts.get(i).getMerchantId())) {
                    if(merchantInventoryDtos.get(j).getStock() > carts.get(i).getItemCount()) {
                        merchantInventoryDtos.get(j).setStock(merchantInventoryDtos.get(j).getStock() - carts.get(i).getItemCount());
                        merchantInventoryDtos.get(j).setStockSold(merchantInventoryDtos.get(j).getStockSold() + carts.get(i).getItemCount());
                        change = merchantInventoryDtos.get(j);
                        id = productDTO.getProductId();
                        apiClient.editMerchantProduct(id, change);
                        valid.setIsValid(true);
                    }

                }
            }

//            apiClient.editMerchantProduct()

        }


//        ProductDTO productDTO = apiClient.findById();
//        List<MerchantInventoryDto> merchantInventoryDtos = productDTO.getMerchants();
//        for(int i = 0; i < merchantInventoryDtos.size(); i++) {
//            if(merchantInventoryDtos.get(i).getMerchantId().equals(cart.getMerchantId())) {
//                merchantInventoryDtos.get(i).setStock(merchantInventoryDtos.get(i).getStock()-1);
//            }
//        }
        User user=userRepository.findById(userId).get();
        List<History> historyList=user.getHistoryList();
        for(int i=0;i<user.getCartList().size();i++){
            History history=new History();
            history.setNumberOfItems(user.getCartList().get(i).getItemCount());
            history.setMerchantId(user.getCartList().get(i).getMerchantId());
            history.setProductId(user.getCartList().get(i).getProductId());
            history.setDateOfPurchase(String.valueOf(LocalDate.now()));
            historyList.add(history);
        }
        user.setCartList(new ArrayList<Cart>());
        user.setHistoryList(historyList);
        userRepository.save(user).getHistoryList();
        valid.setIsValid(true);
        return valid;
    }


}
