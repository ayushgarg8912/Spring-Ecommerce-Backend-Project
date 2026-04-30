package com.SpringBoot.service;

import com.SpringBoot.model.Seller;
import com.SpringBoot.model.SellerReport;

public interface SellerReportService {

    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
