package dev.fleet.cost.controller;

import dev.fleet.cost.dto.CreateTripCostRequest;
import dev.fleet.cost.dto.TripCostResponse;
import dev.fleet.cost.service.TripCostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip-costs")
@RequiredArgsConstructor
public class TripCostController {

    private final TripCostService tripCostService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TripCostResponse calculate(@Valid @RequestBody CreateTripCostRequest request) {
        return tripCostService.calculate(request);
    }

    @GetMapping("/transport-requests/{transportRequestId}")
    public TripCostResponse getByTransportRequestId(@PathVariable Long transportRequestId) {
        return tripCostService.getTransportRequestId(transportRequestId);
    }
}
