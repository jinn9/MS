package com.github.jinn9.order.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private Long orderId;
    private Long deliveryId;
    private int statusCode;
    private String statusMessage;
}
