package dev.fleet.controller;


import dev.fleet.entity.Vehicle;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@Transactional
@AutoConfigureMockMvc
public class VehicleControllerE2ETest {

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
    private VehicleRepository vehicleRepository;

    @Test
    void createVehicleThroughControllerAndSaveInDatabase() throws Exception {
        String json = """
                {
                    "registrationNumber": "AB1234",
                    "vehicleType": "BUS",
                    "loadCapacityKg": 20,
                    "passengerCapacity": 35,
                    "vehicleCondition": "GOOD"
                }
                """;

        mockMvc.perform(post("/vehicles").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.registrationNumber").value("AB1234"))
                .andExpect(jsonPath("$.vehicleType").value("BUS"))
                .andExpect(jsonPath("$.loadCapacityKg").value(20))
                .andExpect(jsonPath("$.passengerCapacity").value(35))
                .andExpect(jsonPath("$.vehicleCondition").value("GOOD"));

        Vehicle savedVehicle = vehicleRepository.findAll().getFirst();

        assertThat(savedVehicle.getId()).isNotNull();
        assertThat(savedVehicle.getRegistrationNumber()).isEqualTo("AB1234");
        assertThat(savedVehicle.getVehicleType()).isEqualTo(VehicleType.BUS);
        assertThat(savedVehicle.getLoadCapacityKg()).isEqualTo(20);
        assertThat(savedVehicle.getPassengerCapacity()).isEqualTo(35);
        assertThat(savedVehicle.getVehicleCondition()).isEqualTo(VehicleCondition.GOOD);
    }
}
