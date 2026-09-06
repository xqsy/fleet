package dev.fleet.dto.request;

import dev.fleet.validation.ValidLicenseNumber;
import dev.fleet.validation.ValidName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateDriverRequest(

        @ValidName
        @Size(max = 50)
        String firstName,

        @ValidName
        @Size(max = 50)
        String lastName,

        @ValidLicenseNumber
        String licenseNumber,

        @NotNull
        Boolean isActive
) {
}
