package com.github.jinn9.order.service;

import com.github.jinn9.order.api.domain.Member;
import com.github.jinn9.order.api.domain.Product;
import com.github.jinn9.order.api.service.MemberFeignClient;
import com.github.jinn9.order.api.service.ProductFeignClient;
import com.github.jinn9.order.entity.Order;
import com.github.jinn9.order.entity.OrderProduct;
import com.github.jinn9.order.exception.OrderNotFoundException;
import com.github.jinn9.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberFeignClient memberFeignClient;
    private final ProductFeignClient productFeignClient;

    @Transactional
    public void createOrder(Long memberId, Map<Long, Integer> productsMap) {
        Member member = memberFeignClient.findMember(memberId).getBody();

        List<Product> products = productFeignClient.findProducts(new ArrayList<>(productsMap.keySet())).getBody();

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (Product product : products) {
            int count = productsMap.get(product.getId());
            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, product.getPrice(), count);

            // FIXME: if there is a product with not enough stock, all of the products cannot be ordered
            productFeignClient.removeStock(product.getId(), count);

            orderProducts.add(orderProduct);
        }

        Order order = Order.createOrder(member, orderProducts);

        orderRepository.save(order);
    }

    public Order findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                        .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    public Member findOrderMember(Long memberId) {
        return memberFeignClient.findMember(memberId).getBody();
    }

    public List<Product> findOrderProducts(List<Long> productIds) {
        return productFeignClient.findProducts(productIds).getBody();
    }
}
