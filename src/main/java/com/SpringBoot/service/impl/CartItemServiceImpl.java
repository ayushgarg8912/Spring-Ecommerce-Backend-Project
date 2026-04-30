package com.SpringBoot.service.impl;

import com.SpringBoot.model.CartItem;
import com.SpringBoot.model.User;
import com.SpringBoot.repository.CartItemRepository;
import com.SpringBoot.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {

        CartItem item =findCartItemById(id);

            User cartItemUser=item.getCart().getUser();

            if(cartItemUser.getId().equals(userId)){
                item.setQuantity(cartItem.getQuantity());
                item.setMrpPrice(item.getQuantity()*item.getProduct().getMrpPrice());
                item.setSellingPrice(item.getQuantity()*item.getProduct().getSellingPrice());
                return cartItemRepository.save(item);
            }

        throw new Exception("you can't update this cartitem");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws Exception {

        CartItem item =findCartItemById(cartItemId);

        User cartItemUser=item.getCart().getUser();

        if(cartItemUser.getId().equals(userId)){
            cartItemRepository.delete(item);
        }

        else throw new Exception("you can't delete this item");

    }

    @Override
    public CartItem findCartItemById(Long id) throws Exception {
        return cartItemRepository.findById(id).orElseThrow(()->
                new Exception("cart Item not found with id "+id ));
    }
}
