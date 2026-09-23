package dev.fleet.controller;


import dev.fleet.entity.Driver;
import dev.fleet.entity.Vehicle;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import dev.fleet.repository.DriverRepository;
import dev.fleet.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@Transactional
@AutoConfigureMockMvc
public class DriverControllerE2ETest {

    @Container
    @ServiceConnection
    static final PostgreSQLContainer postgresContainer =
            new PostgreSQLContainer("postgres:18")
                    .withDatabaseName("fleet_test")
                    .withUsername("postgres")
                    .withPassword("postgres");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void assignVehicleToDriverThroughController() throws Exception {
        Driver driver = driverRepository.save(
                Driver.builder().firstName("Ivan").lastName("Ivanov").licenseNumber("ABC12345678").build()
        );

        Vehicle vehicle = vehicleRepository.save(
                Vehicle.builder()
                        .registrationNumber("AB1234")
                        .vehicleType(VehicleType.BUS)
                        .loadCapacityKg(20)
                        .passengerCapacity(35)
                        .vehicleCondition(VehicleCondition.GOOD)
                        .build()
        );

        String json = """
                {
                    "vehicleId": %d
                }
                """.formatted(vehicle.getId());

        mockMvc.perform(
                put("/drivers/{id}/vehicle", driver.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(driver.getId()))
                .andExpect(jsonPath("$.vehicleId").value(vehicle.getId()))
                .andExpect(jsonPath("$.firstName").value("Ivan"))
                .andExpect(jsonPath("$.lastName").value("Ivanov"));

        Driver updatedDriver = driverRepository.findById(driver.getId()).orElseThrow();

        assertThat(updatedDriver.getVehicle().getId()).isEqualTo(vehicle.getId());
    }
}
