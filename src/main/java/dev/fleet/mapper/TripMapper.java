package dev.fleet.mapper;

import dev.fleet.dto.response.TripResponse;
import dev.fleet.entity.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(target = "requestId", source = "transportRequest.id")
    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "vehicleId", source = "vehicle.id")
    TripResponse toResponse(Trip trip);
}