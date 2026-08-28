package dev.fleet.mapper;

import dev.fleet.dto.request.CreateVehicleRequest;
import dev.fleet.dto.request.UpdateVehicleRequest;
import dev.fleet.dto.response.VehicleResponse;
import dev.fleet.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    Vehicle toEntity(CreateVehicleRequest vehicleRequest);

    VehicleResponse toResponse(Vehicle vehicle);

    List<VehicleResponse> toResponseList(List<Vehicle> vehicles);

    void update(UpdateVehicleRequest request, @MappingTarget Vehicle vehicle);
}
