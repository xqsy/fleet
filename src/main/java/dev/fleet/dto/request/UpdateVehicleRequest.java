package dev.fleet.dto.request;

import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;

public record UpdateVehicleRequest(
        String registrationNumber,
        VehicleType vehicleType,
        Integer loadCapacityKg,
        Integer passengerCapacity,
        VehicleCondition vehicleCondition
) {
}
