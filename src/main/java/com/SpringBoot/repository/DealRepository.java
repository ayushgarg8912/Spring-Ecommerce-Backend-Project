package com.SpringBoot.repository;

import com.SpringBoot.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal,Long> {
}
