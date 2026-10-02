package dev.fleet.cost.mapper;


import dev.fleet.cost.document.TripCost;
import dev.fleet.cost.dto.CreateTripCostRequest;
import dev.fleet.cost.dto.TripCostResponse;
import org.mapstruct.Mapper;

import java.math.BigDecimal;
import java.time.Instant;

@Mapper(componentModel = "spring")
public interface TripCostMapper {

    TripCost toEntity(
            CreateTripCostRequest request,
            BigDecimal fuelLiters,
            BigDecimal fuelCost,
            Instant calculatedAt
    );

    TripCostResponse toResponse(TripCost tripCost);
}
