package dev.fleet.dto.response;

import java.time.Instant;

public record ErrorResponse(
        String errorMessage,
        int statusCode,
        Instant timestamp,
        String path
) {
}
