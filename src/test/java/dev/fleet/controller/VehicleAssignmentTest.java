package dev.fleet.controller;


import com.jayway.jsonpath.JsonPath;
import dev.fleet.dto.request.AssignVehicleRequest;
import dev.fleet.dto.request.CreateDriverRequest;
import dev.fleet.dto.request.CreateVehicleRequest;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import dev.fleet.repository.DriverRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class VehicleAssignmentTest {

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

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void cleanUp() {
        driverRepository.deleteAllInBatch();
        vehicleRepository.deleteAllInBatch();
    }

    @Test
    void assignVehicleToDriverThroughController() throws Exception {
        String driverJson = objectMapper.writeValueAsString(new CreateDriverRequest("Ivan", "Ivanov", "ABC12345678"));

        String driverResponse = mockMvc.perform(
                post("/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(driverJson)
                )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long driverId = JsonPath.parse(driverResponse).read("$.id", Long.class);

        String vehicleJson = objectMapper.writeValueAsString(
                new CreateVehicleRequest("AB1234", VehicleType.BUS, 20, 35, VehicleCondition.GOOD));

        String vehicleResponse = mockMvc.perform(
                post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson)
                )
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long vehicleId = JsonPath.parse(vehicleResponse).read("$.id", Long.class);

        String assignVehicleJson = objectMapper.writeValueAsString(new AssignVehicleRequest(vehicleId));

        mockMvc.perform(
                put("/drivers/{id}/vehicle", driverId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(assignVehicleJson)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(driverId))
                .andExpect(jsonPath("$.vehicleId").value(vehicleId))
                .andExpect(jsonPath("$.firstName").value("Ivan"))
                .andExpect(jsonPath("$.lastName").value("Ivanov"));

        mockMvc.perform(get("/drivers/{id}", driverId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleId").value(vehicleId));
    }
}
