package dev.fleet.dto.request;

import dev.fleet.entity.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record UpdateTransportRequest(

        @NotBlank
        @Size(max = 250)
        String departureAddress,

        @NotBlank
        @Size(max = 250)
        String destinationAddress,

        @NotNull
        VehicleType requiredVehicleType,

        @NotNull
        @PositiveOrZero
        Integer cargoWeightKg,

        @NotNull
        @PositiveOrZero
        Integer passengerCount,

        String requestDescription
) {
}
