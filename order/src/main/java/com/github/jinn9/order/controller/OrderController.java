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

import java.util.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/orders")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody @Validated OrderRequestDto orderRequestDto) {

        Map<Long, Integer> productMap = createProductMap(orderRequestDto);

        orderService.createOrder(orderRequestDto.getMemberId(), productMap);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.value(), "Order created"));
    }

    @GetMapping("/api/orders/{orderId}")
    public ResponseEntity<OrderResponseDto> findOrder(@PathVariable(name = "orderId") Long orderId) {
        Order order = orderService.findOrder(orderId);

        Member member = orderService.findMember(order.getMemberId());

        List<OrderProduct> orderProducts = order.getOrderProducts();

        List<Product> products = findProducts(orderProducts);

        // a map that maps product to orderProduct
        Map<Product, OrderProduct> productOrderProductMap = createProductOrderProductMap(products, orderProducts);

        List<OrderResponseDto.ProductResponseDto> productResponseDtos = createProductResponseDtos(products, productOrderProductMap);

        OrderResponseDto orderResponseDto = createOrderResponseDto(order, member, productResponseDtos);

        return ResponseEntity.ok(orderResponseDto);
    }

    /**
     * @return a map whose key is product id and value is product count
     */
    private Map<Long, Integer> createProductMap(OrderRequestDto orderRequestDto) {
        Map<Long, Integer> productsMap = new HashMap<>();
        for (OrderRequestDto.ProductRequestDto productRequestDto : orderRequestDto.getProducts()) {
            productsMap.put(productRequestDto.getProductId(),
                    productsMap.getOrDefault(productRequestDto.getProductId(),0) + productRequestDto.getCount());
        }
        return productsMap;
    }
    private List<Product> findProducts(List<OrderProduct> orderProducts) {
        Set<Long> productIds = new HashSet<>();
        orderProducts.forEach(orderProduct -> productIds.add(orderProduct.getProductId()));
        return orderService.findProducts(productIds);
    }

    private Map<Product, OrderProduct> createProductOrderProductMap(List<Product> products, List<OrderProduct> orderProducts) {
        Map<Product, OrderProduct> productOrderProductMap = new HashMap<>();
        products.forEach(product -> {
            OrderProduct orderProduct = orderProducts.stream().filter(op -> op.getProductId().equals(product.getId()))
                    .findFirst().orElseThrow(() -> new RuntimeException("OrderProduct not found"));
            productOrderProductMap.put(product, orderProduct);
        });
        return productOrderProductMap;
    }

    private List<OrderResponseDto.ProductResponseDto> createProductResponseDtos(List<Product> products, Map<Product, OrderProduct> productOrderProductMap) {
        List<OrderResponseDto.ProductResponseDto> productResponseDtos = new ArrayList<>();
        products.forEach(product -> {
            OrderResponseDto.ProductResponseDto productResponseDto = new OrderResponseDto.ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    productOrderProductMap.get(product).getCount());
            productResponseDtos.add(productResponseDto);
        });
        return productResponseDtos;
    }

    private OrderResponseDto createOrderResponseDto(Order order, Member member, List<OrderResponseDto.ProductResponseDto> productResponseDtos) {
        return new OrderResponseDto(
                order.getId(),
                order.getMemberId(),
                member.getEmail(),
                productResponseDtos,
                order.getOrderDate(),
                order.getStatus(),
                order.getTotalPrice()
        );
    }
}
