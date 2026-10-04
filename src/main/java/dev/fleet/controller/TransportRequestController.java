package dev.fleet.controller;

import dev.fleet.dto.request.CreateTransportRequest;
import dev.fleet.dto.request.UpdateTransportRequest;
import dev.fleet.dto.response.TransportRequestResponse;
import dev.fleet.dto.response.TripCostResponse;
import dev.fleet.service.TransportRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transport Requests", description = "Requests management")
@RestController
@RequestMapping("/transport-requests")
@RequiredArgsConstructor
public class TransportRequestController {

    private final TransportRequestService transportRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransportRequestResponse createTransportRequest(
            @Valid @RequestBody CreateTransportRequest createTransportRequest
    ) {
        return transportRequestService.createTransportRequest(createTransportRequest);
    }

    @PostMapping("/{id}/cost")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void calculateTripCost(@PathVariable Long id) {
        transportRequestService.calculateTripCost(id);
    }

    @GetMapping
    public List<TransportRequestResponse> getAllTransportRequests() {
        return transportRequestService.getAllTransportRequests();
    }

    @GetMapping("/{id}")
    public TransportRequestResponse getTransportRequestById(@PathVariable Long id) {
        return transportRequestService.getTransportRequestById(id);
    }

    @GetMapping("/{id}/cost")
    public TripCostResponse getTripCost(@PathVariable Long id) {
        return transportRequestService.getTripCost(id);
    }

    @PutMapping("/{id}")
    public TransportRequestResponse updateTransportRequest(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTransportRequest updateTransportRequest
    ) {
        return transportRequestService.updateTransportRequest(id, updateTransportRequest);
    }

    @PatchMapping("/{id}/cancel")
    public TransportRequestResponse cancelTransportRequest(@PathVariable Long id) {
        return transportRequestService.cancelTransportRequest(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTransportRequest(@PathVariable Long id) {
        transportRequestService.deleteTransportRequest(id);
    }


}
