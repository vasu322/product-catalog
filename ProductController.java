package com.example.productcatalog.controller;

import com.example.productcatalog.model.Product;
import com.example.productcatalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // GET all products with pagination
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? 
                Sort.Direction.DESC : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<Product> products = productService.getAllProducts(pageable);
        
        return ResponseEntity.ok(products);
    }

    // GET product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // POST create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    // PUT update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id, 
            @Valid @RequestBody Product product) {
        
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // GET products by category id with pagination
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<Product>> getProductsByCategoryId(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.getProductsByCategoryId(categoryId, pageable);
        
        return ResponseEntity.ok(products);
    }
}