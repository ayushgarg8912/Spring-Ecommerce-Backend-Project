package com.SpringBoot.repository;

import com.SpringBoot.model.SellerReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerReportRepository extends JpaRepository<SellerReport,Long> {

    SellerReport findBySellerId(Long sellerId);

}
