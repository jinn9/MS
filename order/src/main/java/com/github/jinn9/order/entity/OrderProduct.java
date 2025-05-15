package com.github.jinn9.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderProduct extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    private Long product_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;
}
