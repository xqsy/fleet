package dev.fleet.dto.response;

import java.time.OffsetDateTime;

import dev.fleet.entity.enums.TransportRequestStatus;
import dev.fleet.entity.enums.VehicleType;

public record TransportRequestResponse(
    Long id,
    String departureAddress,
    String destinationAddress,
    VehicleType requiredVehicleType,
    Integer cargoWeightKg,
    Integer passengerCount,
    String requestDescription,
    TransportRequestStatus requestStatus,
    OffsetDateTime createdAt
) {
}
