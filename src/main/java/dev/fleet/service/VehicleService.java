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

        vehicle.update(
                request.registrationNumber(),
                request.vehicleType(),
                request.loadCapacityKg(),
                request.passengerCapacity(),
                request.vehicleCondition()
        );

        return vehicleMapper.toResponse(vehicle);
    }

    @Transactional
    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicle(id);

        vehicleRepository.delete(vehicle);
    }

    private Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }
}
