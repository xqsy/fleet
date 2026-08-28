package dev.fleet.dto.response;

import java.time.OffsetDateTime;

import dev.fleet.entity.enums.VehicleCondition;

public record TripResponse(
    Long id,
    Long requestId,
    Long driverId,
    Long vehicleId,
    OffsetDateTime startedAt,
    OffsetDateTime completedAt,
    String completionComment,
    VehicleCondition vehicleConditionAfter
) {
}
