package dev.fleet.workload;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Validated
@RequiredArgsConstructor
public class DriverWorkloadService {

    private final DriverWorkloadProperties properties;

    public void validateWorkload(
            @NotNull Duration dailyWorkload,
            @NotNull Duration weeklyWorkload,
            @NotNull Duration restTime
    ) {
        Duration maxDailyWorkload = Duration.ofHours(properties.getMaxDailyHours());

        Duration maxWeeklyWorkload = Duration.ofHours(properties.getMaxWeeklyHours());

        Duration minRestTime = Duration.ofHours(properties.getMinRestHours());

        if (dailyWorkload.compareTo(maxDailyWorkload) > 0) {
            throw new DriverWorkloadLimitException("Driver has reached the daily work limit");
        }

        if (weeklyWorkload.compareTo(maxWeeklyWorkload) > 0) {
            throw new DriverWorkloadLimitException("Driver has reached the weekly work limit");
        }

        if (restTime.compareTo(minRestTime) < 0) {
            throw new DriverWorkloadLimitException("Driver has not had enough rest");
        }
    }
}
