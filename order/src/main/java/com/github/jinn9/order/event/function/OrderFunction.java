package com.github.jinn9.order.event.function;

import com.github.jinn9.order.event.dto.ResponseDto;
import com.github.jinn9.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.function.Consumer;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class OrderFunction {

    private final OrderService orderService;

    @Bean
    public Consumer<ResponseDto> completeOrder() {
        return responseDto -> {
            if (responseDto.getStatusCode() == HttpStatus.OK.value()) {
                orderService.onDeliveryComplete(responseDto.getOrderId(), responseDto.getDeliveryId());

                log.debug("Order " + responseDto.getOrderId() + " has been completed");
            } else {
                // TODO: could add logic to notify ops
            }
        };
    }
}
