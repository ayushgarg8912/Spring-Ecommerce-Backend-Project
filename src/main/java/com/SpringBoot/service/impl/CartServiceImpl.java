package com.SpringBoot.service.impl;

import com.SpringBoot.model.Cart;
import com.SpringBoot.model.CartItem;
import com.SpringBoot.model.Product;
import com.SpringBoot.model.User;
import com.SpringBoot.repository.CartItemRepository;
import com.SpringBoot.repository.CartRepository;
import com.SpringBoot.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addCartItem(User user, Product product, String size, int quantity) {

        Cart cart = findUserCart(user);

        CartItem isPresnent = cartItemRepository.findByCartAndProductAndSize(cart,product,size);


        if(isPresnent==null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(user.getId());
            cartItem.setSize(size);

            int totalPrice=quantity*product.getSellingPrice();
            cartItem.setSellingPrice(totalPrice);
            cartItem.setMrpPrice(quantity*product.getMrpPrice());
            cart.getCartItemSet().add(cartItem);
            cartItem.setCart(cart);

            return cartItemRepository.save(cartItem);

        }

        return isPresnent;
    }

    @Override
    public Cart findUserCart(User user) {

        Cart cart = cartRepository.findByUserId(user.getId());

        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;

        for(CartItem cartItem:cart.getCartItemSet()){
            totalPrice+=cartItem.getMrpPrice();
            totalDiscountedPrice+=cartItem.getSellingPrice();
            totalPrice=cartItem.getQuantity();

            cart.setTotalMrpPrice(totalPrice);
            cart.setTotalItem(totalItem);
            cart.setTotalSellingPrice(totalDiscountedPrice);
            cart.setDiscount(calculateDiscountPercantage(totalPrice,totalDiscountedPrice));
            cart.setTotalItem(totalItem);
        }

        return cart;
    }

    private int calculateDiscountPercantage(int mrpPrice, int sellerPrice) {

        if(mrpPrice<=1){
            return 0;
        }
        double discount = mrpPrice-sellerPrice;
        double discountPercentage = (discount/mrpPrice)*100;
        return (int)discountPercentage;
    }
}
