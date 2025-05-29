package com.github.jinn9.delivery.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    private String city;
    private String province;

    public Address(String city, String province) {
        this.city = city;
        this.province = province;
    }
}

