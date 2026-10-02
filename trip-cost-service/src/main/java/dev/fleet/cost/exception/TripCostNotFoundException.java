package dev.fleet.cost.exception;

public class TripCostNotFoundException extends RuntimeException {
    public TripCostNotFoundException(Long transportRequestId) {
        super("Trip cost not found for transport request: " + transportRequestId);
    }
}
