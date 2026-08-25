package dev.fleet.dto.response;

public record DriverResponse(
    Long id,
    String firstName,
    String lastName,
    String licenseNumber,
    Long vehicleId,
    Boolean isActive
) {
}
