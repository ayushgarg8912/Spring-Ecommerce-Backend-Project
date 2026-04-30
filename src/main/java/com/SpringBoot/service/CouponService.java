package com.SpringBoot.service;

import com.SpringBoot.model.Cart;
import com.SpringBoot.model.Coupon;
import com.SpringBoot.model.User;

import java.util.List;

public interface CouponService {

    Cart applyCoupon(String code, double orderValue, User user) throws Exception;
    Cart removeCoupon(String code,User user) throws Exception;
    Coupon findCoupon(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    List<Coupon> findAllCoupons();
    void deleteCoupon(Long id) throws Exception;

}
