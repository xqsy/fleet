package dev.fleet.workload;

import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor
public class DriverWorkloadService {

    private final DriverWorkloadProperties properties;

    public void validateWorkload(Duration dailyWorkload, Duration weeklyWorkload, Duration restTime) {
        Duration maxDailyWorkload = Duration.ofHours(properties.getMaxDailyHours());

        Duration maxWeeklyWorkload = Duration.ofHours(properties.getMaxWeeklyHours());

        Duration minRestTime = Duration.ofHours(properties.getMinRestHours());

        if (dailyWorkload.compareTo(maxDailyWorkload) >= 0) {
            throw new DriverWorkloadLimitException("Driver has reached the daily work limit");
        }

        if (weeklyWorkload.compareTo(maxWeeklyWorkload) >= 0) {
            throw new DriverWorkloadLimitException("Driver has reached the weekly work limit");
        }

        if (restTime.compareTo(minRestTime) < 0) {
            throw new DriverWorkloadLimitException("Driver has not had enough rest");
        }
    }
}
