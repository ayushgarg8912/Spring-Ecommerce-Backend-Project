package com.SpringBoot.Controller;

import com.SpringBoot.exception.ProductException;
import com.SpringBoot.model.Product;
import com.SpringBoot.model.User;
import com.SpringBoot.model.Wishlist;
import com.SpringBoot.repository.WishListRepository;
import com.SpringBoot.service.ProductService;
import com.SpringBoot.service.UserService;
import com.SpringBoot.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishListController {

    private final WishListService wishListService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<Wishlist> getWishListByUserId(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Wishlist wishlist = wishListService.getWishListByUserId(user);

        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProductToWishList(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        Product product =productService.findProductById(productId);
        User user =userService.findUserByJwtToken(jwt);
        Wishlist updatedWishList = wishListService.addProductToWishList(
                user,
                product
        );

        return ResponseEntity.ok(updatedWishList);

    }




}
