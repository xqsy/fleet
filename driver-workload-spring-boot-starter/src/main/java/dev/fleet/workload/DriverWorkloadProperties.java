package dev.fleet.workload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "fleet.driver-workload")
public class DriverWorkloadProperties {
    private boolean enabled = true;
    private int maxWeeklyHours = 40;
    private int maxDailyHours = 12;
    private int minRestHours = 8;
}
