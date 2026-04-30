package com.SpringBoot.service;

import com.SpringBoot.model.Product;
import com.SpringBoot.model.User;
import com.SpringBoot.model.Wishlist;

public interface WishListService {

    Wishlist createWishList(User user);
    Wishlist getWishListByUserId(User user);
    Wishlist addProductToWishList(User user, Product product);



}
