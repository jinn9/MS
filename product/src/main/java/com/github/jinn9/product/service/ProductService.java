package com.github.jinn9.product.service;

import com.github.jinn9.product.dto.ProductRequestDto;
import com.github.jinn9.product.dto.ProductResponseDto;
import com.github.jinn9.product.entity.Product;
import com.github.jinn9.product.exception.ProductExistsException;
import com.github.jinn9.product.exception.ResourceNotFoundException;
import com.github.jinn9.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequestDto productRequestDto) {
        Optional<Product> productOptional = productRepository.findByName(productRequestDto.getName());
        if (productOptional.isPresent()) {
            throw new ProductExistsException("This product already exists.");
        }

        Product product = new Product(productRequestDto.getName(), productRequestDto.getPrice(), productRequestDto.getStockQuantity());
        productRepository.save(product);
    }

    public ProductResponseDto findProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "id", productId.toString()));

        return new ProductResponseDto(product.getName(), product.getPrice(), product.getStockQuantity());
    }
}
