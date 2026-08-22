package dev.fleet.service;

import dev.fleet.dto.request.AssignDriverRequest;
import dev.fleet.dto.request.CompleteTripRequest;
import dev.fleet.dto.response.TripResponse;
import dev.fleet.entity.Driver;
import dev.fleet.entity.TransportRequest;
import dev.fleet.entity.Trip;
import dev.fleet.entity.Vehicle;
import dev.fleet.entity.enums.TransportRequestStatus;
import dev.fleet.exception.DriverNotFoundException;
import dev.fleet.exception.TransportRequestNotFoundException;
import dev.fleet.exception.TripNotFoundException;
import dev.fleet.mapper.TripMapper;
import dev.fleet.repository.DriverRepository;
import dev.fleet.repository.TransportRequestRepository;
import dev.fleet.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final TransportRequestRepository transportRequestRepository;
    private final DriverRepository driverRepository;
    private final TripMapper tripMapper;

    @Transactional
    public TripResponse assignDriver(Long transportRequestId, AssignDriverRequest assignDriverRequest) {
        TransportRequest transportRequest = transportRequestRepository.findById(transportRequestId)
                .orElseThrow(() -> new TransportRequestNotFoundException(transportRequestId));

        Driver driver = driverRepository.findById(assignDriverRequest.driverId())
                .orElseThrow(() -> new DriverNotFoundException(assignDriverRequest.driverId()));

        Trip trip = Trip.create(transportRequest, driver);
        Trip savedTrip = tripRepository.save(trip);

        transportRequest.changeStatus(TransportRequestStatus.ASSIGNED);

        return tripMapper.toResponse(savedTrip);
    }

    @Transactional
    public TripResponse startTrip(Long id) {
        Trip trip = getTrip(id);

        trip.start();

        trip.getTransportRequest().changeStatus(TransportRequestStatus.IN_PROGRESS);

        return tripMapper.toResponse(trip);
    }

    @Transactional
    public TripResponse completeTrip(Long id, CompleteTripRequest completeTripRequest) {
        Trip trip = getTrip(id);

        trip.complete(completeTripRequest.completionComment(), completeTripRequest.vehicleConditionAfter());

        trip.getVehicle().changeCondition(completeTripRequest.vehicleConditionAfter());

        trip.getTransportRequest().changeStatus(TransportRequestStatus.COMPLETED);

        return tripMapper.toResponse(trip);
    }

    private Trip getTrip(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new TripNotFoundException(id));
    }
}
