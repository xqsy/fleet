package dev.fleet.dao.querybyexample;


import dev.fleet.entity.Driver;
import dev.fleet.entity.Vehicle;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import dev.fleet.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverQbeDao {

    private final DriverRepository driverRepository;

    public List<Driver> getActiveDrivers() {
        Driver probe = Driver.builder().build();

        Example<Driver> example = Example.of(probe);

        return driverRepository.findAll(example);
    }

    public List<Driver> getActiveDriversOnBus() {
        Vehicle vehicleProbe = Vehicle.builder()
                .vehicleType(VehicleType.BUS)
                .vehicleCondition(VehicleCondition.GOOD)
                .build();

        Driver driverProbe = Driver.builder().build();
        driverProbe.assignVehicle(vehicleProbe);

        Example<Driver> example = Example.of(driverProbe);

        return driverRepository.findAll(example);
    }
}
