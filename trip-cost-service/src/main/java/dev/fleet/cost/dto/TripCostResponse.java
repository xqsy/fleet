package dev.fleet.cost.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record TripCostResponse(
        String id,
        Long transportRequestId,
        Integer distanceKm,
        BigDecimal fuelLiters,
        BigDecimal fuelCost,
        Instant calculatedAt
) {
}
