package com.SpringBoot.repository;

import com.SpringBoot.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {

    Coupon findByCode(String code);

}
