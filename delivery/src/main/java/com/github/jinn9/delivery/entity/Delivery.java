package com.github.jinn9.delivery.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Delivery extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private Long orderId;

    @Embedded
    private Address address;

    public Delivery(Long orderId, Address address) {
        this.orderId = orderId;
        this.address = address;
    }
}
