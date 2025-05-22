package com.github.jinn9.order.api.service;

import com.github.jinn9.order.api.domain.Product;
import com.github.jinn9.order.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("product")
public interface ProductFeignClient {

    @GetMapping("/api/products")
    ResponseEntity<List<Product>> findProducts(@RequestParam(name = "productIds", required = false) List<Long> productIds);

    @PostMapping("/api/products/{productId}/add")
    public ResponseEntity<ResponseDto> addStock(@PathVariable(name = "productId") Long productId,
                                                @RequestParam(name = "stockQuantity") int stockQuantity);

    @PostMapping("/api/products/{productId}/remove")
    public ResponseEntity<ResponseDto> removeStock(@PathVariable(name = "productId") Long productId,
                                                   @RequestParam(name = "stockQuantity") int stockQuantity);
}
