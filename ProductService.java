package com.example.productcatalog.service;

import com.example.productcatalog.exception.ResourceNotFoundException;
import com.example.productcatalog.model.Category;
import com.example.productcatalog.model.Product;
import com.example.productcatalog.repository.CategoryRepository;
import com.example.productcatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    
    @Transactional
    public Product createProduct(Product product) {
        
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", product.getCategory().getId()));
        
        product.setCategory(category);
        return productRepository.save(product);
    }

    
    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);
        
        
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        
        
        if (productDetails.getCategory() != null && productDetails.getCategory().getId() != null) {
            Category category = categoryRepository.findById(productDetails.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productDetails.getCategory().getId()));
            product.setCategory(category);
        }
        
        return productRepository.save(product);
    }

    
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    
    public Page<Product> getProductsByCategoryId(Long categoryId, Pageable pageable) {
        
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", "id", categoryId);
        }
        
        return productRepository.findByCategoryId(categoryId, pageable);
    }
}
