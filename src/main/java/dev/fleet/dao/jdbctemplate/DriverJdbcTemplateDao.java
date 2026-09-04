package dev.fleet.dao.jdbctemplate;


import dev.fleet.dao.jdbc.DriverJdbcRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverJdbcTemplateDao {

    private final JdbcTemplate jdbcTemplate;

    public List<DriverJdbcRecord> getActiveDrivers() {
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

        return jdbcTemplate.query(sql, this::mapDriver);
    }

    public List<DriverJdbcRecord> getActiveDriversOnBus() {
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

        return jdbcTemplate.query(sql, this::mapDriver);
    }

    private DriverJdbcRecord mapDriver(ResultSet resultSet, int rowNum) throws SQLException {
        return new DriverJdbcRecord(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("license_number"),
                resultSet.getObject("vehicle_id", Long.class),
                resultSet.getBoolean("is_active")
        );
    }
}
