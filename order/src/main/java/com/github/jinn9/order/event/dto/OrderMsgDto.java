package com.github.jinn9.order.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderMsgDto {
    private Long orderId;
    private AddressMsgDto address;

    @Data
    @AllArgsConstructor
    public static class AddressMsgDto {
        private String city;
        private String province;
    }
}
