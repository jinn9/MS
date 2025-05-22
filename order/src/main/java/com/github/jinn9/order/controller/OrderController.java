package com.github.jinn9.order.controller;

import com.github.jinn9.order.api.domain.Member;
import com.github.jinn9.order.api.domain.Product;
import com.github.jinn9.order.dto.OrderRequestDto;
import com.github.jinn9.order.dto.OrderResponseDto;
import com.github.jinn9.order.dto.ResponseDto;
import com.github.jinn9.order.entity.Order;
import com.github.jinn9.order.entity.OrderProduct;
import com.github.jinn9.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody @Validated OrderRequestDto orderRequestDto) {

        Map<Long, Integer> productsMap = new HashMap<>();
        for (OrderRequestDto.ProductRequestDto productRequestDto : orderRequestDto.getProducts()) {
            productsMap.put(productRequestDto.getProductId(),
                    productsMap.getOrDefault(productRequestDto.getProductId(),0) + productRequestDto.getCount());
        }

        orderService.createOrder(orderRequestDto.getMemberId(), productsMap);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(), "Order created"));
    }

    @GetMapping("/api/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> findOrder(@PathVariable(name = "orderId") Long orderId) {
        Order order = orderService.findOrder(orderId);

        Member member = orderService.findOrderMember(order.getMemberId());

        List<OrderProduct> orderProducts = order.getOrderProducts();

        List<Long> productIds = new ArrayList<>();
        orderProducts.forEach(orderProduct -> productIds.add(orderProduct.getProductId()));
        List<Product> products = orderService.findOrderProducts(productIds);

        // map product to orderProduct
        Map<Product, OrderProduct> productOderProductMap = new HashMap<>();
        products.forEach(product -> {
            OrderProduct orderProduct = orderProducts.stream().filter(op -> op.getProductId().equals(product.getId()))
                    .findFirst().orElseThrow(() -> new RuntimeException("OrderProduct not found"));
            productOderProductMap.put(product, orderProduct);
        });

        List<OrderResponseDto.ProductResponseDto> productResponseDtos = new ArrayList<>();
        products.forEach(product -> {
            OrderResponseDto.ProductResponseDto productResponseDto = new OrderResponseDto.ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    productOderProductMap.get(product).getCount());
            productResponseDtos.add(productResponseDto);
        });

        OrderResponseDto orderResponseDto = new OrderResponseDto(
                order.getId(),
                order.getMemberId(),
                member.getEmail(),
                productResponseDtos,
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalPrice()
        );
        return ResponseEntity.ok(orderResponseDto);
    }
}
