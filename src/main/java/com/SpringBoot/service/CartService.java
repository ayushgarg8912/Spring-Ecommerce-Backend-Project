package com.SpringBoot.service;

import com.SpringBoot.model.Cart;
import com.SpringBoot.model.CartItem;
import com.SpringBoot.model.Product;
import com.SpringBoot.model.User;

public interface CartService {

    public CartItem addCartItem(
            User user,
            Product product,
            String size,
            int quantity
    );
    public Cart findUserCart(User user);
}
