package dev.fleet.controller;

import dev.fleet.dto.request.AssignDriverRequest;
import dev.fleet.dto.request.CompleteTripRequest;
import dev.fleet.dto.response.TripResponse;
import dev.fleet.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping("/transport-requests/{transportRequestId}/trip")
    @ResponseStatus(HttpStatus.CREATED)
    public TripResponse assignDriver(
            @PathVariable Long transportRequestId,
            @Valid @RequestBody AssignDriverRequest assignDriverRequest
    ) {
        return tripService.assignDriver(transportRequestId, assignDriverRequest);
    }

    @GetMapping("/trips")
    public List<TripResponse> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/trips/{id}")
    public TripResponse getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    @PatchMapping("/trips/{id}/start")
    public TripResponse startTrip(@PathVariable Long id) {
        return tripService.startTrip(id);
    }

    @PatchMapping("/trips/{id}/complete")
    public TripResponse completeTrip(
            @PathVariable Long id,
            @Valid @RequestBody CompleteTripRequest completeTripRequest
    ) {
        return tripService.completeTrip(id, completeTripRequest);
    }

}
