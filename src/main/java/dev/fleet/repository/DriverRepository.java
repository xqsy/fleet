package dev.fleet.repository;

import dev.fleet.entity.Driver;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver> {

    List<Driver> findByIsActiveTrue();

    @EntityGraph(attributePaths = "trips")
    List<Driver> findWithTripsByIsActiveTrue();

    List<Driver> findByIsActiveTrueAndVehicleVehicleTypeAndVehicleVehicleCondition(
            VehicleType vehicleType,
            VehicleCondition vehicleCondition
    );

    Page<Driver> findByIsActiveTrue(Pageable pageable);

    Page<Driver> findByIsActiveTrueAndVehicleVehicleTypeAndVehicleVehicleCondition(
            VehicleType vehicleType,
            VehicleCondition vehicleCondition,
            Pageable pageable
    );
}
