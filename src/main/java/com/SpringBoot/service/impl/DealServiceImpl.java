package com.SpringBoot.service.impl;

import com.SpringBoot.model.Deal;
import com.SpringBoot.model.HomeCategory;
import com.SpringBoot.repository.DealRepository;
import com.SpringBoot.repository.HomeCategoryRepository;
import com.SpringBoot.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final HomeCategoryRepository homeCategoryRepository;

    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {

        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);


       Deal newdeal = dealRepository.save(deal);
       newdeal.setCategory(category);
       newdeal.setDiscount(deal.getDiscount());

        return dealRepository.save(newdeal);
    }

    @Override
    public Deal updateDeal(Deal deal,Long id) throws Exception {

        Deal existingDeal =dealRepository.findById(id).orElseThrow(null);

        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);

        if(existingDeal!=null){
            if(deal.getDiscount()!=null){
                existingDeal.setDiscount(deal.getDiscount());
            }
            if(category!=null){
                existingDeal.setCategory(category);
            }
            return dealRepository.save(existingDeal);
        }
        throw new Exception("Deal not found");


    }

    @Override
    public void deletedeal(Long id) throws Exception {
      Deal deal =dealRepository.findById(id).orElseThrow(()->
              new Exception("deal not found"));
      dealRepository.delete(deal);
    }

}
