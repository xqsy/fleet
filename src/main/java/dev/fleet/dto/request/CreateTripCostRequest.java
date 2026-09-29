package dev.fleet.dto.request;

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
