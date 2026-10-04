package dev.fleet.cost.service;

import dev.fleet.cost.config.TripCostProperties;
import dev.fleet.cost.document.TripCost;
import dev.fleet.cost.dto.CreateTripCostRequest;
import dev.fleet.cost.dto.TripCostResponse;
import dev.fleet.cost.exception.TripCostNotFoundException;
import dev.fleet.cost.mapper.TripCostMapper;
import dev.fleet.cost.messaging.TripCostEventPublisher;
import dev.fleet.cost.repository.TripCostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TripCostService {

    private final TripCostRepository tripCostRepository;
    private final TripCostMapper tripCostMapper;
    private final TripCostProperties tripCostProperties;
    private final TripCostEventPublisher tripCostEventPublisher;

    public TripCostResponse calculate(CreateTripCostRequest request) {
        Integer distanceKm = request.distanceKm();

        BigDecimal fuelLiters = BigDecimal.valueOf(distanceKm).multiply(tripCostProperties.getFuelConsumptionPerKm());

        BigDecimal fuelCost = fuelLiters.multiply(tripCostProperties.getFuelPricePerLiter());

        TripCost tripCost = tripCostRepository.save(
                tripCostMapper.toEntity(request, fuelLiters, fuelCost, Instant.now()));

        TripCostResponse response = tripCostMapper.toResponse(tripCost);

        tripCostEventPublisher.publish(response);

        return response;
    }

    public TripCostResponse getTransportRequestId(Long transportRequestId) {
        TripCost tripcost = tripCostRepository.findByTransportRequestId(transportRequestId)
                .orElseThrow(() -> new TripCostNotFoundException(transportRequestId));

        return tripCostMapper.toResponse(tripcost);
    }
}
