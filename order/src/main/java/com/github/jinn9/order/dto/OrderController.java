package com.github.jinn9.order.dto;

import com.github.jinn9.order.entity.Order;
import com.github.jinn9.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody @Validated OrderRequestDto orderRequestDto) {
        orderService.createOrder(orderRequestDto.getMemberId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(), "Order created"));
    }

    @GetMapping("/api/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> findOrder(@PathVariable(name = "orderId") Long orderId) {
        Order order = orderService.findOrder(orderId);
        OrderResponseDto orderResponseDto = new OrderResponseDto(
                order.getId(),
                order.getMemberId(),
                order.getOrderDate(),
                order.getStatus()
        );
        return ResponseEntity.ok(orderResponseDto);
    }
}
