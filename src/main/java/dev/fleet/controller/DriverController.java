package dev.fleet.controller;

import dev.fleet.dto.filter.DriverFilter;
import dev.fleet.dto.request.AssignVehicleRequest;
import dev.fleet.dto.request.CreateDriverRequest;
import dev.fleet.dto.request.UpdateDriverRequest;
import dev.fleet.dto.response.DriverResponse;
import dev.fleet.service.DriverService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Drivers", description = "Driver management")
@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverResponse createDriver(@Valid @RequestBody CreateDriverRequest createDriverRequest) {
        return driverService.createDriver(createDriverRequest);
    }

    @GetMapping
    public Page<DriverResponse> getAllDrivers(
            @Valid DriverFilter driverFilter,
            @PageableDefault(page = 0, size = 15, sort = "lastName", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return driverService.getAllDrivers(driverFilter, pageable);
    }

    @GetMapping("/{id}")
    public DriverResponse getDriverById(@PathVariable Long id) {
        return driverService.getDriverById(id);
    }

    @PutMapping("/{id}")
    public DriverResponse updateDriver(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDriverRequest updateDriverRequest
    ) {
        return driverService.updateDriver(id, updateDriverRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }

    @PutMapping("/{id}/vehicle")
    public DriverResponse assignVehicle(
            @PathVariable Long id,
            @Valid @RequestBody AssignVehicleRequest assignVehicleRequest
    ) {
        return driverService.assignVehicle(id, assignVehicleRequest);
    }
}
