package dev.fleet.dto.request;

public record UpdateDriverRequest(
        String firstName,
        String lastName,
        String licenseNumber,
        boolean isActive
) {
}
