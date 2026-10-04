package dev.fleet.cost.messaging;

import dev.fleet.cost.config.RabbitMqConfig;
import dev.fleet.cost.dto.CreateTripCostRequest;
import dev.fleet.cost.service.TripCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripCostMessageListener {

    private final TripCostService tripCostService;

    @RabbitListener(queues = RabbitMqConfig.CALCULATE_TRIP_COST_QUEUE)
    public void listen(CreateTripCostRequest message) {
        tripCostService.calculate(new CreateTripCostRequest(message.transportRequestId(), message.distanceKm()));
    }
}
