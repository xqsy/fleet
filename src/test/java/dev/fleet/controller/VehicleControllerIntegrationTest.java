package dev.fleet.controller;


import dev.fleet.dto.request.CreateVehicleRequest;
import dev.fleet.entity.Vehicle;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import dev.fleet.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class VehicleControllerIntegrationTest {

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

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanUp() {
        vehicleRepository.deleteAllInBatch();
    }

    @Test
    void createVehicleThroughControllerAndSaveInDatabase() throws Exception {
        String json = objectMapper.writeValueAsString(
                new CreateVehicleRequest("AB1234", VehicleType.BUS, 20, 35, VehicleCondition.GOOD)
        );

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

    @Test
    void getNonExistingVehicleReturnsNotFound() throws Exception {
        mockMvc.perform(get("/vehicles/{id}", 12345L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.path").value("/vehicles/12345"));
    }

    @Test

    void createVehicleWithInvalidValuesReturnsBadRequest() throws Exception {
        String json = objectMapper.writeValueAsString(
                new CreateVehicleRequest("AA1111", VehicleType.BUS, -1, -1, VehicleCondition.GOOD));

        mockMvc.perform(post("/vehicles").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest());
    }
}
