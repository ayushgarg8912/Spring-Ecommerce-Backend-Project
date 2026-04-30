package com.SpringBoot.repository;

import com.SpringBoot.model.Cart;
import com.SpringBoot.model.CartItem;
import com.SpringBoot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long >{

    CartItem findByCartAndProductAndSize(Cart cart, Product product , String size);

}
