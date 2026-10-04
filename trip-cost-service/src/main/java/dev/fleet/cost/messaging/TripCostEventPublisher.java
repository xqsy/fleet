package dev.fleet.cost.messaging;

import dev.fleet.cost.dto.TripCostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripCostEventPublisher {

    private final KafkaTemplate<String, TripCostResponse> kafkaTemplate;

    public void publish(TripCostResponse event) {
        kafkaTemplate.send("trip-cost.calculated", event.transportRequestId().toString(), event);
    }
}
