package dev.fleet.entity;

import java.time.OffsetDateTime;

import dev.fleet.entity.enums.VehicleCondition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false)
    private TransportRequest transportRequest;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "started_at")
    private OffsetDateTime startedAt;

    @Column(name = "completed_at")
    private OffsetDateTime completedAt;

    @Column(name = "completion_comment")
    private String completionComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_condition_after")
    private VehicleCondition vehicleConditionAfter;

    @Builder
    public Trip(
            TransportRequest transportRequest,
            Driver driver,
            Vehicle vehicle
    ) {
        this.transportRequest = transportRequest;
        this.driver = driver;
        this.vehicle = vehicle;
    }

    public static Trip create(TransportRequest transportRequest, Driver driver) {
        return Trip.builder()
                .transportRequest(transportRequest)
                .driver(driver)
                .vehicle(driver.getVehicle())
                .build();
    }

    public void start() {
        this.startedAt = OffsetDateTime.now();
    }

    public void complete(
            String completionComment,
            VehicleCondition vehicleConditionAfter
    ) {
        this.completedAt = OffsetDateTime.now();
        this.completionComment = completionComment;
        this.vehicleConditionAfter = vehicleConditionAfter;
    }
}
