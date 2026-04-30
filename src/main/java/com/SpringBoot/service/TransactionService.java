package com.SpringBoot.service;

import com.SpringBoot.model.Order;
import com.SpringBoot.model.Seller;
import com.SpringBoot.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);

    List<Transaction> getTransactionBySellerId(Seller seller);

    List<Transaction> getAllTransaction();



}
