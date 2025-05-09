package com.github.jinn9.order.service;

import com.github.jinn9.order.entity.Order;
import com.github.jinn9.order.entity.OrderStatus;
import com.github.jinn9.order.exception.ResourceNotFoundException;
import com.github.jinn9.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder() {
        Order order = new Order();
        order.setStatus(OrderStatus.READY);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    public Order findOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("Order", "id", orderId.toString())
        );
    }
}
