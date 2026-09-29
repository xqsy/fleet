package dev.fleet.cost.dto;

import java.time.Instant;

public record ErrorResponse(
        String errorMessage,
        int statusCode,
        Instant timestamp,
        String path
) {
}
