package dev.fleet.dto.request;

public record CreateDriverRequest(
    String firstName,
    String lastName,
    String licenseNumber
) {
}
