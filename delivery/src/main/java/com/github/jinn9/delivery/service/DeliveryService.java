package com.github.jinn9.delivery.service;

import com.github.jinn9.delivery.entity.Delivery;
import com.github.jinn9.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public void createDelivery(Delivery delivery) {
        deliveryRepository.save(delivery);
    }
}
