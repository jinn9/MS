package com.github.jinn9.order.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private int statusCode;
    private String statusMessage;
}
