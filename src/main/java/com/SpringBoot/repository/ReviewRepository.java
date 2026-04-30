package com.SpringBoot.repository;

import com.SpringBoot.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review ,Long> {

    List<Review> findByProductId(Long productId);

}
