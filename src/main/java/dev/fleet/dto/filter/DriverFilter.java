package dev.fleet.dto.filter;

import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import jakarta.validation.constraints.PositiveOrZero;

public record DriverFilter(

        Boolean isActive,

        VehicleType vehicleType,

        VehicleCondition vehicleCondition,

        @PositiveOrZero
        Integer minLoadCapacityKg,

        @PositiveOrZero
        Integer minPassengerCount
) {
}
