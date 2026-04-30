package com.SpringBoot.service.impl;

import com.SpringBoot.model.Order;
import com.SpringBoot.model.Seller;
import com.SpringBoot.model.Transaction;

import com.SpringBoot.repository.SellerRepository;
import com.SpringBoot.repository.TransactionRepository;
import com.SpringBoot.service.TransactionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

   private final TransactionRepository transactionRepository;
   private final SellerRepository sellerRepository;

    @Override
    public Transaction createTransaction(Order order) {

        Seller seller = sellerRepository.findById(order.getSellerId()).get();

        Transaction transaction = new Transaction();
        transaction.setSeller(seller);
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionBySellerId(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }
}
