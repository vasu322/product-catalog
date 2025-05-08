package com.example.productcatalog.repository;

import com.example.productcatalog.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Find products by category id with pagination
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
}