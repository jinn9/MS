package com.github.jinn9.order.controller;

import com.github.jinn9.order.dto.OrderResponseDto;
import com.github.jinn9.order.dto.ResponseDto;
import com.github.jinn9.order.entity.Order;
import com.github.jinn9.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<ResponseDto> createOrder() {
        orderService.createOrder();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(), "Order created successfully"));
    }

    @GetMapping("/api/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> findOrder(@PathVariable("orderId") Long orderId) {
        Order order = orderService.findOrder(orderId);
        OrderResponseDto orderResponseDto = new OrderResponseDto(order.getId(), order.getMemberId(),
                order.getOrderDate(),order.getStatus());
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseDto);
    }
}
