package dev.fleet.cost.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateTripCostRequest(

        @NotNull
        @Positive
        Long transportRequestId,

        @NotNull
        @Positive
        Integer distanceKm
) {
}
