package dev.fleet.dto.request;

import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateVehicleRequest(

    @NotBlank
    @Size(max = 30)
    String registrationNumber,

    @NotNull
    VehicleType vehicleType,

    @NotNull
    @PositiveOrZero
    Integer loadCapacityKg,

    @NotNull
    @PositiveOrZero
    Integer passengerCapacity,

    @NotNull
    VehicleCondition vehicleCondition
) {   
} 
