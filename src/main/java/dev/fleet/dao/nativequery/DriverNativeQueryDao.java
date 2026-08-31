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
        String sql = "SELECT * FROM drivers WHERE is_active = true";

        return entityManager.createNativeQuery(sql, Driver.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Driver> getActiveDriversOnBus() {
        String sql = """
                SELECT d.* FROM drivers d JOIN vehicles v ON v.id = d.vehicle_id
                WHERE d.is_active = true AND v.vehicle_type = 'BUS' AND v.vehicle_condition = 'GOOD'
                """;

        return entityManager.createNativeQuery(sql, Driver.class).getResultList();
    }
}
