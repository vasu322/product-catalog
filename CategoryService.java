package com.example.productcatalog.service;

import com.example.productcatalog.exception.ResourceNotFoundException;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    
    @Transactional
    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = getCategoryById(id);
        
        category.setName(categoryDetails.getName());
        category.setDescription(categoryDetails.getDescription());
        
        return categoryRepository.save(category);
    }

    
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
}
