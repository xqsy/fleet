package dev.fleet.controller;

import dev.fleet.dto.request.AssignVehicleRequest;
import dev.fleet.dto.request.CreateDriverRequest;
import dev.fleet.dto.request.UpdateDriverRequest;
import dev.fleet.dto.response.DriverResponse;
import dev.fleet.service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<DriverResponse> getAllDrivers() {
        return driverService.getAllDrivers();
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
