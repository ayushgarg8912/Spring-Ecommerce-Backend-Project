package com.SpringBoot.service.impl;

import com.SpringBoot.model.Seller;
import com.SpringBoot.model.SellerReport;
import com.SpringBoot.repository.SellerReportRepository;
import com.SpringBoot.service.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {

    private final SellerReportRepository sellerReportRepository;


    @Override
    public SellerReport getSellerReport(Seller seller) {

        SellerReport sellerReport = sellerReportRepository.findBySellerId(seller.getId());

        if(sellerReport==null){

            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);

        }

        return sellerReport;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {
        return sellerReportRepository.save(sellerReport);
    }
}
