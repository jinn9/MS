package com.github.jinn9.order.api.service;

import com.github.jinn9.order.api.domain.Product;
import com.github.jinn9.order.api.exception.ApiException;
import com.github.jinn9.order.dto.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFallback implements ProductFeignClient {
    @Override
    public ResponseEntity<List<Product>> findProducts(List<Long> productIds) {
        throw new ApiException("Failed to call Product.findProducts");
    }

    @Override
    public ResponseEntity<ResponseDto> addStock(Long productId, int stockQuantity) {
        throw new ApiException("Failed to call Product.addStock");
    }

    @Override
    public ResponseEntity<ResponseDto> removeStock(Long productId, int stockQuantity) {
        throw new ApiException("Failed to call Product.removeStock");
    }
}
