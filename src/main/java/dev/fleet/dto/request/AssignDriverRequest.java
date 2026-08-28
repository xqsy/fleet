package dev.fleet.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AssignDriverRequest(

    @NotNull
    @Positive
    Long driverId
) {
}
