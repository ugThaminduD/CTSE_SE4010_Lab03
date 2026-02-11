package com.sliit.product_service.controller;

import com.sliit.product_service.entity.Product;
import com.sliit.product_service.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // 1️⃣ POST - Create Product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return repository.save(product);
    }
    // Bulk Create Products
    @PostMapping("/bulk")
    public List<Product> createProducts(@RequestBody List<Product> products) {
        return repository.saveAll(products);
    }

    // 2️⃣ GET - Get All Products
    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // 3️⃣ GET by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // 4️⃣ DELETE
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
        return "Product deleted successfully";
    }
}