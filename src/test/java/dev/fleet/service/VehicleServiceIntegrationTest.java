package dev.fleet.service;

import dev.fleet.dto.request.CreateVehicleRequest;
import dev.fleet.dto.request.UpdateVehicleRequest;
import dev.fleet.dto.response.VehicleResponse;
import dev.fleet.entity.Vehicle;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import dev.fleet.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Testcontainers
public class VehicleServiceIntegrationTest {

    @Container
    @ServiceConnection
    static final PostgreSQLContainer postgresContainer =
            new PostgreSQLContainer("postgres:18")
                    .withDatabaseName("fleet_test")
                    .withUsername("postgres")
                    .withPassword("postgres");

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void createVehicleAndSaveInDatabase() {
        CreateVehicleRequest request =
                new CreateVehicleRequest("AB1234", VehicleType.BUS, 20, 35, VehicleCondition.GOOD);

        VehicleResponse response = vehicleService.createVehicle(request);

        assertNotNull(response.id());

        Vehicle savedVehicle = vehicleRepository.findById(response.id()).orElseThrow();

        assertThat(savedVehicle.getRegistrationNumber()).isEqualTo(request.registrationNumber());
        assertThat(savedVehicle.getVehicleType()).isEqualTo(request.vehicleType());
        assertThat(savedVehicle.getLoadCapacityKg()).isEqualTo(request.loadCapacityKg());
        assertThat(savedVehicle.getPassengerCapacity()).isEqualTo(request.passengerCapacity());
        assertThat(savedVehicle.getVehicleCondition()).isEqualTo(request.vehicleCondition());
    }

    @Test
    void updateVehicleAndSaveInDatabase() {
        UpdateVehicleRequest request =
                new UpdateVehicleRequest("CD5678", VehicleType.TRUCK, 5000, 2, VehicleCondition.NEEDS_REPAIR);

        Vehicle vehicle = vehicleRepository.save(
                Vehicle.builder()
                        .registrationNumber("AB1234")
                        .vehicleType(VehicleType.BUS)
                        .loadCapacityKg(20)
                        .passengerCapacity(35)
                        .vehicleCondition(VehicleCondition.GOOD)
                        .build()
        );

        VehicleResponse response = vehicleService.updateVehicle(vehicle.getId(), request);

        Vehicle updatedVehicle = vehicleRepository.findById(vehicle.getId()).orElseThrow();

        assertThat(updatedVehicle.getRegistrationNumber()).isEqualTo(response.registrationNumber());
        assertThat(updatedVehicle.getVehicleType()).isEqualTo(response.vehicleType());
        assertThat(updatedVehicle.getLoadCapacityKg()).isEqualTo(response.loadCapacityKg());
        assertThat(updatedVehicle.getPassengerCapacity()).isEqualTo(response.passengerCapacity());
        assertThat(updatedVehicle.getVehicleCondition()).isEqualTo(response.vehicleCondition());
    }
}
