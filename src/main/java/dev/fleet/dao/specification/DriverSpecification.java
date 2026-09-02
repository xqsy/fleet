package dev.fleet.dao.specification;

import dev.fleet.entity.Driver;
import dev.fleet.entity.Vehicle;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class DriverSpecification {

    public static Specification<Driver> isActive() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("isActive"));
    }

    public static Specification<Driver> hasVehicleType(VehicleType type) {
        return (root, query, criteriaBuilder) -> {
            Join<Driver, Vehicle> vehicle = root.join("vehicle");
            return criteriaBuilder.equal(vehicle.get("vehicleType"), type);
        };
    }

    public static Specification<Driver> hasVehicleCondition(VehicleCondition condition) {
        return (root, query, criteriaBuilder) -> {
            Join<Driver, Vehicle> vehicle = root.join("vehicle");
            return criteriaBuilder.equal(vehicle.get("vehicleCondition"), condition);
        };
    }
}
