package dev.fleet.client;

import dev.fleet.dto.request.CreateTripCostRequest;
import dev.fleet.dto.response.TripCostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class TripCostClient {

    private final RestTemplate restTemplate;

    @Value("${trip-cost-service.url}")
    private String tripCostServiceUrl;

    public TripCostResponse calculate(CreateTripCostRequest request) {
        return restTemplate.postForObject(tripCostServiceUrl + "/trip-costs", request, TripCostResponse.class);
    }

    public TripCostResponse getByTransportRequestId(Long transportRequestId) {
        return restTemplate.getForObject(
                tripCostServiceUrl + "/trip-costs/transport-requests/" + transportRequestId,
                TripCostResponse.class
        );
    }
}
