package com.github.jinn9.order.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    private Long id;
    private String email;
    private Address address;
}
