package com.SpringBoot.service.impl;

import com.SpringBoot.model.Cart;
import com.SpringBoot.model.Coupon;
import com.SpringBoot.model.User;
import com.SpringBoot.repository.CartRepository;
import com.SpringBoot.repository.CouponRepository;
import com.SpringBoot.repository.UserRepository;
import com.SpringBoot.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private  final CouponRepository couponRepository;
    private  final CartRepository cartRepository;
    private final UserRepository userRepository;


    @Override
    public Cart applyCoupon(String code, double orderValue, User user) throws Exception {
            Coupon coupon = couponRepository.findByCode(code);

            Cart cart =cartRepository.findByUserId(user.getId());

            if(coupon==null){
                throw new Exception("coupon not valid");
            }
            if(user.getUsedcoupons().contains(coupon)){
                throw new Exception("coupon already used");
            }
            if(orderValue<coupon.getMinimumOrderValue()){
                throw new Exception("value for minimum order value"+coupon.getMinimumOrderValue());
            }
            if(coupon.isActive() && LocalDate.now().isAfter(coupon.getValidityStartDate()) && LocalDate.now().isBefore(coupon.getValidityEndDate())){

                user.getUsedcoupons().add(coupon);
                userRepository.save(user);
                double discountPrice = (cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;

                cart.setTotalSellingPrice(cart.getTotalSellingPrice()-discountPrice);
                cart.setCouponCode(code);
                cartRepository.save(cart);
                return  cart;
            }

        throw new Exception("coupon not valid");
    }

    @Override
    public Cart removeCoupon(String code, User user) throws Exception {

   Coupon coupon = couponRepository.findByCode(code);

   if(coupon==null){
       throw new Exception("coupon not found");
   }
        Cart cart =cartRepository.findByUserId(user.getId());

        double discountPrice = (cart.getTotalSellingPrice()*coupon.getDiscountPercentage())/100;

        cart.setTotalSellingPrice(cart.getTotalSellingPrice()+discountPrice);
        cart.setCouponCode(null);


        return cartRepository.save(cart);
    }

    @Override
    public Coupon findCoupon(Long id) throws Exception {
        return couponRepository.findById(id).orElseThrow(()->
                new Exception("coupon not found"));
    }

    @Override
    @PreAuthorize("hasRoll ('ADMIN')")
    public Coupon createCoupon(Coupon coupon) {


        return couponRepository.save(coupon);
    }

    @Override
    public List<Coupon> findAllCoupons() {


        return couponRepository.findAll();
    }

    @Override
    @PreAuthorize("hasRoll ('ADMIN')")
    public void deleteCoupon(Long id) throws Exception {

        findCoupon(id);
        couponRepository.deleteById(id);


    }
}
