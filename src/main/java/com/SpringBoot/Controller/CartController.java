package com.SpringBoot.Controller;


import com.SpringBoot.exception.ProductException;
import com.SpringBoot.model.Cart;
import com.SpringBoot.model.CartItem;
import com.SpringBoot.model.Product;
import com.SpringBoot.model.User;
import com.SpringBoot.request.AddItemRequest;
import com.SpringBoot.response.ApiResponse;
import com.SpringBoot.service.CartItemService;
import com.SpringBoot.service.CartService;
import com.SpringBoot.service.ProductService;
import com.SpringBoot.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCarHandler(@RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);

        Cart cart=cartService.findUserCart(user);

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt)
            throws ProductException , Exception {

        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItem item = cartService.addCartItem(user,
                product,
                req.getSize(),
                req.getQuantity());

        ApiResponse res = new ApiResponse();
        res.setMessage("Item Added To Cart Successfully");



        return new ResponseEntity<>(item,HttpStatus.OK);

    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartHandler(@PathVariable Long cartItemId,
                                                        @RequestHeader("Authorization")String jwt) throws Exception {

        User user=userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(),cartItemId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Item remove from cart");

        return new ResponseEntity<>(res,HttpStatus.OK);

    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem>updateCartItemHandler(@PathVariable Long cartItemId,
                                                         @RequestBody CartItem cartItem,
                                                         @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        CartItem updateCartItem = null;
        if (cartItem.getQuantity() > 0) {
            updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
        }
        return new ResponseEntity<>(updateCartItem,HttpStatus.ACCEPTED);
    }






}
