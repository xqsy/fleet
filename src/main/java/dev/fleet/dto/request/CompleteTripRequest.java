package dev.fleet.dto.request;

import dev.fleet.entity.enums.VehicleCondition;

public record CompleteTripRequest(
    String completionComment,
    VehicleCondition vehicleConditionAfter
) {
}
