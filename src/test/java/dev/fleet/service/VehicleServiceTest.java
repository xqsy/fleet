package dev.fleet.service;

import dev.fleet.dto.request.CreateVehicleRequest;
import dev.fleet.dto.response.VehicleResponse;
import dev.fleet.entity.Vehicle;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import dev.fleet.exception.VehicleNotFoundException;
import dev.fleet.mapper.VehicleMapper;
import dev.fleet.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void createAndSaveVehicle () {
        CreateVehicleRequest request = new CreateVehicleRequest(
                "AB1234",
                VehicleType.BUS,
                20,
                35,
                VehicleCondition.GOOD
        );

        Vehicle vehicle = Vehicle.builder()
                .registrationNumber("AB1234")
                .vehicleType(VehicleType.BUS)
                .loadCapacityKg(20)
                .passengerCapacity(35)
                .vehicleCondition(VehicleCondition.GOOD)
                .build();

        VehicleResponse mockedResponse = new VehicleResponse(
            1L,
            "AB1234",
            VehicleType.BUS,
            20,
            35,
            VehicleCondition.GOOD
        );

        when(vehicleMapper.toEntity(request)).thenReturn(vehicle);
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        when(vehicleMapper.toResponse(vehicle)).thenReturn(mockedResponse);

        VehicleResponse actualResponse = vehicleService.createVehicle(request);

        assertEquals(mockedResponse, actualResponse);

        verify(vehicleMapper).toEntity(request);
        verify(vehicleRepository).save(vehicle);
        verify(vehicleMapper).toResponse(vehicle);
    }

    @Test
    void getVehicleByIdThrowsNotFoundException() {
        Long vehicleId = 111L;

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        assertThrows(VehicleNotFoundException.class, () -> vehicleService.getVehicleById(vehicleId));

        verify(vehicleRepository).findById(vehicleId);
    }
}
