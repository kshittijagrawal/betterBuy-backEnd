package com.example.UserClient.services;

import com.example.UserClient.dto.*;
import com.example.UserClient.entity.User;

import java.util.List;

public interface UserService {
    public Validation loginValidation(String userId, String userPassword);
    public Validation addUser(User user);
    public Validation addToCart(Cart cart,String userId);
    public Validation updateCartItem(Cart cart,String userId);
    public Validation deleteFromCart(String userId, String productId, String merchantId);
    public List<CartList> viewCart(String userId);
    public Validation checkOut(String userId);
    public List<History> viewHistory(String userId);
    public Validation removeFromCart(Cart cart, String userId);
    public Validation removeCart(String userId);
}
