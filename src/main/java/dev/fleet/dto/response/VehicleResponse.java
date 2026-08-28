package dev.fleet.dto.response;

import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;

public record VehicleResponse(
    Long id,
    String registrationNumber,
    VehicleType vehicleType,
    Integer loadCapacityKg,
    Integer passengerCapacity,
    VehicleCondition vehicleCondition
) {
}
