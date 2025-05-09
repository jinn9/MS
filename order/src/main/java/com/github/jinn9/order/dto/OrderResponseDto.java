package com.github.jinn9.order.dto;

import com.github.jinn9.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long memberId;
    private LocalDateTime orderDate;
    private OrderStatus status;
}
