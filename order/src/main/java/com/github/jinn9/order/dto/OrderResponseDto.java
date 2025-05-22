package com.github.jinn9.order.dto;

import com.github.jinn9.order.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long memberId;
    private String memberEmail;
    private List<ProductResponseDto> products;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private int totalPrice;

    @Data
    @AllArgsConstructor
    public static class ProductResponseDto {
        private Long id;
        private String name;
        private int price;
        private int count;
    }
}
