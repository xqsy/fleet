package dev.fleet.dao.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverJdbcDao {

    private final DataSource dataSource;

    public List<DriverJdbcRecord> getActiveDrivers() {
        List<DriverJdbcRecord> drivers = new ArrayList<>();

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

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
                ) {
            while (resultSet.next()) {
                drivers.add(mapDriver(resultSet));
            }

            return drivers;
        } catch (SQLException e) {
        throw new RuntimeException("Failed to get drivers", e);
        }

    }

    public List<DriverJdbcRecord> getActiveDriversOnBus() {
        List<DriverJdbcRecord> drivers = new ArrayList<>();

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

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                drivers.add(mapDriver(resultSet));
            }

            return drivers;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get drivers", e);
        }
    }

    private DriverJdbcRecord mapDriver(ResultSet resultSet) throws SQLException {
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
