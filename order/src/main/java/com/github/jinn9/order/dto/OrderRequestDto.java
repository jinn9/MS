package com.github.jinn9.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDto {
    @NotNull(message = "memberId cannot be null")
    private Long memberId;
    @Valid
    @NotNull(message = "products cannot be null")
    private List<ProductRequestDto> products;

    @Data
    @AllArgsConstructor
    public static class ProductRequestDto {
        @NotNull(message = "productId cannot be null")
        private Long productId;

        @NotNull(message = "count cannot be null")
        @Min(value = 0, message = "count cannot be negative")
        private Integer count;
    }
}


