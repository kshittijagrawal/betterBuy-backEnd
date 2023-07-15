package com.example.UserClient.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartResponseList {
    public List<CartList> getCartResponse() {
        return cartResponse;
    }

    public void setCartResponse(List<CartList> cartResponse) {
        this.cartResponse = cartResponse;
    }

    List<CartList> cartResponse;
}
