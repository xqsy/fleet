package dev.fleet.mapper;

import dev.fleet.dto.request.CreateVehicleRequest;
import dev.fleet.dto.response.VehicleResponse;
import dev.fleet.entity.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toEntity(CreateVehicleRequest vehicleRequest);

    VehicleResponse toResponse(Vehicle vehicle);

}
