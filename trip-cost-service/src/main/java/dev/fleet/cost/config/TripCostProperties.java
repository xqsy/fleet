package dev.fleet.cost.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "trip-cost")
public class TripCostProperties {

    private BigDecimal fuelConsumptionPerKm;
    private BigDecimal fuelPricePerLiter;
}
