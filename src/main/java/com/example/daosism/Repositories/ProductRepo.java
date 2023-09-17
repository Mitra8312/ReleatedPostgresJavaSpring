package com.example.daosism.Repositories;

import com.example.daosism.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    Product findProductByArticle(String article);
}
