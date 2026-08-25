package dev.fleet.dto.request;

import dev.fleet.entity.enums.VehicleCondition;
import jakarta.validation.constraints.NotNull;

public record CompleteTripRequest(

    String completionComment,

    @NotNull
    VehicleCondition vehicleConditionAfter
) {
}
