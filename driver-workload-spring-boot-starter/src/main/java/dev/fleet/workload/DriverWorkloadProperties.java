package dev.fleet.workload;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "fleet.driver-workload")
public class DriverWorkloadProperties {
    private boolean enabled = true;

    @Min(1)
    private int maxWeeklyHours = 40;

    @Min(1)
    private int maxDailyHours = 12;

    @Min(1)
    private int minRestHours = 8;
}
