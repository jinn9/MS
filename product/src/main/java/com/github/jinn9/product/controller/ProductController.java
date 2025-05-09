package com.github.jinn9.product.controller;

import com.github.jinn9.product.dto.ProductRequestDto;
import com.github.jinn9.product.dto.ProductResponseDto;
import com.github.jinn9.product.dto.ResponseDto;
import com.github.jinn9.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/products")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody @Validated ProductRequestDto productRequestDto) {
        productService.createProduct(productRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(), "Product created successfully"));
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable("productId") Long productId) {
        ProductResponseDto productResponseDto = productService.findProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }
}
