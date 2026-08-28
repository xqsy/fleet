package dev.fleet.service;

import dev.fleet.dto.request.CreateTransportRequest;
import dev.fleet.dto.request.UpdateTransportRequest;
import dev.fleet.dto.response.TransportRequestResponse;
import dev.fleet.entity.TransportRequest;
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

        transportRequestMapper.update(request, transportRequest);

        return transportRequestMapper.toResponse(transportRequest);
    }

    @Transactional
    public void deleteTransportRequest(Long id) {
        TransportRequest transportRequest = getTransportRequest(id);

        transportRequestRepository.delete(transportRequest);
    }

    @Transactional
    public TransportRequestResponse cancelTransportRequest(Long id) {
        TransportRequest request = getTransportRequest(id);

        request.cancel();

        return transportRequestMapper.toResponse(request);
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