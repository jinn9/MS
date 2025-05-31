package com.github.jinn9.order.service;

import com.github.jinn9.order.api.domain.Member;
import com.github.jinn9.order.api.domain.Product;
import com.github.jinn9.order.api.exception.ApiException;
import com.github.jinn9.order.api.service.MemberFeignClient;
import com.github.jinn9.order.api.service.ProductFeignClient;
import com.github.jinn9.order.entity.Order;
import com.github.jinn9.order.entity.OrderProduct;
import com.github.jinn9.order.enumeration.OrderStatus;
import com.github.jinn9.order.event.dto.OrderMsgDto;
import com.github.jinn9.order.exception.OrderNotFoundException;
import com.github.jinn9.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberFeignClient memberFeignClient;
    private final ProductFeignClient productFeignClient;
    private final StreamBridge streamBridge;

    /**
     *
     * @param memberId   - member id
     * @param productMap - a map whose key is product id and value is product count
     */
    @Transactional
    public void createOrder(Long memberId, Map<Long, Integer> productMap) {
        Member member = findMember(memberId);

        List<Product> products = findProducts(productMap.keySet());

        List<OrderProduct> orderProducts = createOrderProducts(productMap, products);

        Order order = Order.createOrder(member, orderProducts);

        Order savedOrder = orderRepository.save(order);

        log.debug("Order created. id: " + savedOrder.getId());

        // send an event to event broker
        sendCommunication(savedOrder, member);
    }

    public Member findMember(Long memberId) {
        return memberFeignClient.findMember(memberId).getBody();
    }

    public List<Product> findProducts(Set<Long> productIds) {
        List<Product> products = productFeignClient.findProducts(new ArrayList<>(productIds)).getBody();

        if (products.size() < productIds.size()) {
            throw new ApiException("Product not found");
        }

        return products;
    }

    public Order findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                        .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    @Transactional
    public void onDeliveryComplete(Long orderId, Long deliveryId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setDeliveryId(deliveryId);
        order.setStatus(OrderStatus.COMPLETE);
    }

    private List<OrderProduct> createOrderProducts(Map<Long, Integer> productMap, List<Product> products) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (Product product : products) {
            int count = productMap.get(product.getId());
            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, product.getPrice(), count);

            productFeignClient.removeStock(product.getId(), count);

            orderProducts.add(orderProduct);
        }
        return orderProducts;
    }

    private void sendCommunication(Order order, Member member) {
        OrderMsgDto.AddressMsgDto address = new OrderMsgDto.AddressMsgDto(member.getAddress().getCity(),
                member.getAddress().getProvince());
        OrderMsgDto orderMsgDto = new OrderMsgDto(order.getId(), address);

        log.info("Sending Communication request for the details: {}", orderMsgDto);

        boolean result = streamBridge.send("sendCommunication-out-0", orderMsgDto);

        log.info("Is the Communication request successfully triggered ? : {}", result);
    }
}
