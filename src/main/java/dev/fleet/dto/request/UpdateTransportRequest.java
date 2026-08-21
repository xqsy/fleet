package dev.fleet.dto.request;

import dev.fleet.entity.enums.VehicleType;

public record UpdateTransportRequest(
        String departureAddress,
        String destinationAddress,
        VehicleType requiredVehicleType,
        Integer cargoWeightKg,
        Integer passengerCount,
        String requestDescription
) {
}
