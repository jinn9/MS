package com.github.jinn9.order.entity;

import com.github.jinn9.order.api.domain.Member;
import com.github.jinn9.order.enumeration.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private Long memberId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }

    // TODO: update the method to receive member, delivery and orderProduct list as arguments
    public static Order createOrder(Member member, List<OrderProduct> orderProducts) {
        Order order = new Order();
        order.setMemberId(member.getId());
        for (OrderProduct orderProduct : orderProducts) {
            order.addOrderProduct(orderProduct);
        }
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public int getTotalPrice() {
        int total = 0;
        for (OrderProduct orderProduct : orderProducts) {
            total += orderProduct.getTotalPrice();
        }
        return total;
    }
}
