package com.github.jinn9.delivery.function;

import com.github.jinn9.delivery.dto.OrderMsgDto;
import com.github.jinn9.delivery.dto.ResponseDto;
import com.github.jinn9.delivery.entity.Address;
import com.github.jinn9.delivery.entity.Delivery;
import com.github.jinn9.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.function.Function;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DeliveryFunction {

    private final DeliveryService deliveryService;

    @Bean
    public Function<OrderMsgDto, ResponseDto> completeDelivery() {
        return orderMsgDto -> {
            Delivery delivery = new Delivery(orderMsgDto.getOrderId(),
                    new Address(orderMsgDto.getAddress() != null ? orderMsgDto.getAddress().getCity() : null,
                            orderMsgDto.getAddress() != null ? orderMsgDto.getAddress().getProvince() : null));

            deliveryService.createDelivery(delivery);

            log.debug("Delivery completed for order " + orderMsgDto.getOrderId());

            return new ResponseDto(delivery.getId(), orderMsgDto.getOrderId(),
                    HttpStatus.OK.value(), "Delivery completed");
        };
    }

}
