package com.example.productcatalog.repository;

import com.example.productcatalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Spring Data JPA provides all basic CRUD operations
    // Custom queries can be added here if needed
}