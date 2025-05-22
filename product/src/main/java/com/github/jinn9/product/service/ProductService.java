package com.github.jinn9.product.service;

import com.github.jinn9.product.entity.Product;
import com.github.jinn9.product.exception.ProductNotFoundException;
import com.github.jinn9.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    public Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product not found"));
    }

    public List<Product> findProducts(List<Long> productIds) {
        return productIds == null ? productRepository.findAll() : productRepository.findProducts(productIds);
    }

    @Transactional
    public void addStock(Long productId, int stockQuantity) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product not found"));

        product.addStock(stockQuantity);
    }

    @Transactional
    public void removeStock(Long productId, int stockQuantity) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product not found"));

        product.removeStock(stockQuantity);
    }
}
