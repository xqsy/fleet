package dev.fleet.service;

import dev.fleet.dto.request.CreateVehicleRequest;
import dev.fleet.dto.request.UpdateVehicleRequest;
import dev.fleet.dto.response.VehicleResponse;
import dev.fleet.entity.Vehicle;
import dev.fleet.exception.VehicleNotFoundException;
import dev.fleet.mapper.VehicleMapper;
import dev.fleet.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Transactional
    public VehicleResponse createVehicle(CreateVehicleRequest request) {
        Vehicle vehicle = vehicleMapper.toEntity(request);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(savedVehicle);
    }

    @Transactional
    public VehicleResponse updateVehicle(Long id, UpdateVehicleRequest request) {
        Vehicle vehicle = getVehicle(id);

        vehicleMapper.update(request, vehicle);

        return vehicleMapper.toResponse(vehicle);
    }

    @Transactional
    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicle(id);

        vehicleRepository.delete(vehicle);
    }

    @Transactional(readOnly = true)
    public VehicleResponse getVehicleById(Long id) {
        return vehicleMapper.toResponse(getVehicle(id));
    }

    @Transactional(readOnly = true)
    public List<VehicleResponse> getAllVehicles() {
        return vehicleMapper.toResponseList(vehicleRepository.findAll());
    }

    private Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }
}
