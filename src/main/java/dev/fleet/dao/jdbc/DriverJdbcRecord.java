package dev.fleet.dao.jdbc;

public record DriverJdbcRecord(
        Long id,
        String firstName,
        String lastName,
        String licenseNumber,
        Long vehicleId,
        boolean isActive
) {
}
