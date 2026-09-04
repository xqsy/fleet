package dev.fleet.dao.jpql;


import dev.fleet.entity.Driver;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverJpqlDao {

    private final EntityManager entityManager;

    public List<Driver> getActiveDrivers() {
        String jpql = "SELECT d FROM Driver d WHERE d.isActive = true";

        return entityManager.createQuery(jpql, Driver.class).getResultList();
    }

    public List<Driver> getActiveDriversOnBus() {
        String jpql = """
                SELECT d FROM Driver d JOIN d.vehicle v
                WHERE d.isActive = true AND v.vehicleType = :vehicleType AND v.vehicleCondition = :vehicleCondition
                """;

        return entityManager
                .createQuery(jpql, Driver.class)
                .setParameter("vehicleType", VehicleType.BUS)
                .setParameter("vehicleCondition", VehicleCondition.GOOD)
                .getResultList();
    }
}
