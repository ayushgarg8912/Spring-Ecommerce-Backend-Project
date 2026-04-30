package com.SpringBoot.service.impl;

import com.SpringBoot.model.Product;
import com.SpringBoot.model.User;
import com.SpringBoot.model.Wishlist;
import com.SpringBoot.repository.WishListRepository;
import com.SpringBoot.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.WatchService;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private  final WishListRepository   wishListRepository;

    @Override
    public Wishlist createWishList(User user) {
      Wishlist wishlist = new Wishlist();
      wishlist.setUser(user);

       return wishListRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishListByUserId(User user) {

        Wishlist wishlist = wishListRepository.findByUserId(user.getId());
        if(wishlist==null){
            wishlist=createWishList(user);
        }

        return wishlist;
    }

    @Override
    public Wishlist addProductToWishList(User user, Product product) {

        Wishlist wishlist = getWishListByUserId(user);

        if(wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }
        else wishlist.getProducts().add(product);

        return wishListRepository.save(wishlist);
    }
}
