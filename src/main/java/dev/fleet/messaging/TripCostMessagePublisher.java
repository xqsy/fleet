package dev.fleet.messaging;

import dev.fleet.config.RabbitMqConfig;
import dev.fleet.dto.request.CreateTripCostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripCostMessagePublisher {
    private final RabbitTemplate rabbitTemplate;

    public void publish(CreateTripCostRequest message) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.TRIP_COST_EXCHANGE, RabbitMqConfig.CALCULATE_TRIP_COST_ROUTING_KEY, message);
    }
}
