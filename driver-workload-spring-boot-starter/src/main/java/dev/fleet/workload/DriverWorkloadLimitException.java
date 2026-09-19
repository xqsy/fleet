package dev.fleet.workload;

public class DriverWorkloadLimitException extends RuntimeException {
    public DriverWorkloadLimitException(String message) {
        super(message);
    }
}
