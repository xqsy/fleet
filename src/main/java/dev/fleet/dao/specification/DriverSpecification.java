package dev.fleet.dao.specification;

import dev.fleet.entity.Driver;
import dev.fleet.entity.Vehicle;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class DriverSpecification {

    public static Specification<Driver> hasActiveStatus(Boolean  isActive) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isActive"), isActive);
    }

    public static Specification<Driver> hasVehicleType(VehicleType type) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(getOrCreateVehicleJoin(root).get("vehicleType"), type);
    }

    public static Specification<Driver> hasVehicleCondition(VehicleCondition condition) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(getOrCreateVehicleJoin(root).get("vehicleCondition"), condition);
    }

    public static Specification<Driver> hasMinLoadCapacityKg(Integer capacity) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(getOrCreateVehicleJoin(root).get("loadCapacityKg"), capacity);
    }

    public static Specification<Driver> hasMinPassengerCapacity(Integer capacity) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(getOrCreateVehicleJoin(root).get("passengerCapacity"), capacity);
    }

    @SuppressWarnings("unchecked")
    private static Join<Driver, Vehicle> getOrCreateVehicleJoin(Root<Driver> root) {
        return root.getJoins().stream()
                .filter(join -> "vehicle".equals(join.getAttribute().getName()))
                .map(join -> (Join<Driver, Vehicle>) join)
                .findFirst()
                .orElseGet(() -> root.join("vehicle"));
    }


}
