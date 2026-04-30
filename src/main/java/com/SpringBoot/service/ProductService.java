package com.SpringBoot.service;

import com.SpringBoot.exception.ProductException;
import com.SpringBoot.model.Product;
import com.SpringBoot.model.Seller;
import com.SpringBoot.request.CreateProductRequest;

import org.springframework.data.domain.Page;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProductService {

    public Product createProduct(CreateProductRequest req , Seller seller);

    public void deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId, Product product) throws ProductException;

    Product findProductById(Long productId) throws ProductException;

    List<Product> searchProducts(String query);

    public Page<Product> getAllProducts(

            String category ,
            String brand,
            String colors,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stocks,
            Integer pageNumber



    );

    List<Product>  getProductBySellerId(Long sellerId);


}
