package dev.fleet.service;

import dev.fleet.dto.request.CreateTransportRequest;
import dev.fleet.dto.request.UpdateTransportRequest;
import dev.fleet.dto.response.TransportRequestResponse;
import dev.fleet.entity.TransportRequest;
import dev.fleet.entity.enums.TransportRequestStatus;
import dev.fleet.exception.TransportRequestNotFoundException;
import dev.fleet.mapper.TransportRequestMapper;
import dev.fleet.repository.TransportRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportRequestService {

    private final TransportRequestRepository transportRequestRepository;
    private final TransportRequestMapper transportRequestMapper;

    @Transactional
    public TransportRequestResponse createTransportRequest(CreateTransportRequest request) {
        TransportRequest transportRequest = transportRequestMapper.toEntity(request);
        TransportRequest savedTransportRequest = transportRequestRepository.save(transportRequest);

        return transportRequestMapper.toResponse(savedTransportRequest);
    }

    @Transactional
    public TransportRequestResponse updateTransportRequest(Long id, UpdateTransportRequest request) {
        TransportRequest transportRequest = getTransportRequest(id);

        transportRequest.update(
                request.departureAddress(),
                request.destinationAddress(),
                request.requiredVehicleType(),
                request.cargoWeightKg(),
                request.passengerCount(),
                request.requestDescription()
        );

        return transportRequestMapper.toResponse(transportRequest);
    }

    @Transactional
    public void deleteTransportRequest(Long id) {
        TransportRequest transportRequest = getTransportRequest(id);

        transportRequestRepository.delete(transportRequest);
    }

    @Transactional
    public TransportRequestResponse changeStatus(Long id, TransportRequestStatus status) {
        TransportRequest transportRequest = getTransportRequest(id);

        transportRequest.changeStatus(status);

        return transportRequestMapper.toResponse(transportRequest);
    }

    @Transactional(readOnly = true)
    public TransportRequestResponse getTransportRequestById(Long id) {
        return transportRequestMapper.toResponse(getTransportRequest(id));
    }

    @Transactional(readOnly = true)
    public List<TransportRequestResponse> getAllTransportRequests() {
        return transportRequestMapper.toResponseList(transportRequestRepository.findAll());
    }

    private TransportRequest getTransportRequest(Long id) {
        return transportRequestRepository.findById(id)
                .orElseThrow(() -> new TransportRequestNotFoundException(id));
    }
}