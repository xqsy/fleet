package dev.fleet.messaging;

import dev.fleet.dto.response.TripCostResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TripCostEventListener {

    @KafkaListener(topics = "trip-cost.calculated")
    public void listen(TripCostResponse event) {
        log.info("Cost of fuel for transportRequestId={} - fuelCost={}", event.transportRequestId(), event.fuelCost());
    }
}
