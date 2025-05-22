package com.github.jinn9.product.controller;

import com.github.jinn9.product.dto.ProductRequestDto;
import com.github.jinn9.product.dto.ProductResponseDto;
import com.github.jinn9.product.dto.ResponseDto;
import com.github.jinn9.product.entity.Product;
import com.github.jinn9.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/products")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody @Validated ProductRequestDto productRequestDto) {
        Product product = new Product(
                productRequestDto.getName(),
                productRequestDto.getPrice(),
                productRequestDto.getStockQuantity()
        );

        productService.createProduct(product);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(), "Product created"));
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable("productId") Long productId) {
        Product product = productService.findProduct(productId);
        ProductResponseDto productResponseDto = new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity()
        );
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponseDto>> findProducts(@RequestParam(name = "productIds", required = false) List<Long> productIds) {
        List<Product> products = productService.findProducts(productIds);
        List<ProductResponseDto> response = new ArrayList<>();
        products.forEach(product -> {
            ProductResponseDto productResponseDto = new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStockQuantity()
            );
            response.add(productResponseDto);
        });
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/api/products/{productId}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable(name = "productId") Long productId,
                                                    @RequestParam(name = "stockQuantity") int stockQuantity) {

        productService.updateStockQuantity(productId, stockQuantity);
        return ResponseEntity.ok(new ResponseDto(HttpStatus.OK.value(), "Product updated"));
    }
}
