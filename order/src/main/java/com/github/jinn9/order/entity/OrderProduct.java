package com.github.jinn9.order.entity;

import com.github.jinn9.order.api.domain.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class OrderProduct extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_product_id")
    private Long id;

    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;

    private int count;

    public static OrderProduct createOrderProduct(Product product, int orderPrice, int count) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductId(product.getId());
        orderProduct.setOrderPrice(orderPrice);
        orderProduct.setCount(count);
        return orderProduct;
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
