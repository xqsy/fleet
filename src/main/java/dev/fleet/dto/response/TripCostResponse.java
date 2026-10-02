package dev.fleet.dto.response;

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
