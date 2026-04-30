package com.SpringBoot.service;

import com.SpringBoot.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealService  {


    List<Deal> getDeals();
    Deal createDeal(Deal deal);
    Deal updateDeal(Deal deal,Long id) throws Exception;
    void deletedeal(Long id) throws Exception;

}
