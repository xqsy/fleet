package dev.fleet.mapper;

import dev.fleet.dto.request.CreateDriverRequest;
import dev.fleet.dto.request.UpdateDriverRequest;
import dev.fleet.dto.response.DriverResponse;
import dev.fleet.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver toEntity(CreateDriverRequest request);

    @Mapping(target = "vehicleId", source = "vehicle.id")
    DriverResponse toResponse(Driver driver);

    List<DriverResponse> toResponseList(List<Driver> drivers);

    void update(UpdateDriverRequest request, @MappingTarget Driver driver);
}
