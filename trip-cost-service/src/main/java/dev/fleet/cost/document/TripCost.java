package dev.fleet.cost.document;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "trip_costs")
public class TripCost {

    @Id
    private String id;

    @Indexed(unique=true)
    private Long transportRequestId;

    private Integer distanceKm;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal fuelLiters;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal fuelCost;

    private Instant calculatedAt;

    @Builder
    public TripCost(
            Long transportRequestId,
            Integer distanceKm,
            BigDecimal fuelLiters,
            BigDecimal fuelCost,
            Instant calculatedAt
    ) {
        this.transportRequestId = transportRequestId;
        this.distanceKm = distanceKm;
        this.fuelLiters = fuelLiters;
        this.fuelCost = fuelCost;
        this.calculatedAt = calculatedAt;
    }
}
