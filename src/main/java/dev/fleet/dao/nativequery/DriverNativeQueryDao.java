package dev.fleet.dao.nativequery;


import dev.fleet.entity.Driver;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverNativeQueryDao {

    private final EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Driver> getActiveDrivers() {
        String sql = """
                SELECT
                    id,
                    first_name,
                    last_name,
                    license_number,
                    vehicle_id,
                    is_active
                FROM drivers
                WHERE is_active = true
                """;

        return entityManager.createNativeQuery(sql, Driver.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Driver> getActiveDriversOnBus() {
        String sql = """
                SELECT
                    d.id,
                    d.first_name,
                    d.last_name,
                    d.license_number,
                    d.vehicle_id,
                    d.is_active
                FROM drivers d
                JOIN vehicles v
                    ON v.id = d.vehicle_id
                WHERE d.is_active = true
                  AND v.vehicle_type = 'BUS'
                  AND v.vehicle_condition = 'GOOD'
                """;

        return entityManager.createNativeQuery(sql, Driver.class).getResultList();
    }
}
