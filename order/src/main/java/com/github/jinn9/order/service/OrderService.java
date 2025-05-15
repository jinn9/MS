package com.github.jinn9.order.service;

import com.github.jinn9.order.entity.Order;
import com.github.jinn9.order.exception.OrderNotFoundException;
import com.github.jinn9.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void createOrder(Long memberId) {
        orderRepository.save(Order.createOrder(memberId));
    }

    public Order findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }


}
