package dev.fleet.entity;

import java.time.OffsetDateTime;

import dev.fleet.entity.enums.TransportRequestStatus;
import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.exception.InvalidOperationException;
import dev.fleet.exception.VehicleNotSuitableException;
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

        Vehicle vehicle = driver.getVehicle();

        if (transportRequest.getRequestStatus() != TransportRequestStatus.NEW) {
            throw new InvalidOperationException("Trip can only be created for a new request");
        }

        if (!driver.getIsActive()) {
            throw new InvalidOperationException("Inactive driver can't be assigned to trip");
        }

        if (driver.getVehicle() == null) {
            throw new InvalidOperationException("Driver has no assigned vehicle");
        }

        if (vehicle.getVehicleType() != transportRequest.getRequiredVehicleType()) {
            throw new VehicleNotSuitableException("Vehicle type does not match transport request");
        }

        if (vehicle.getLoadCapacityKg() < transportRequest.getCargoWeightKg()) {
            throw new VehicleNotSuitableException("Vehicle load capacity doesn't match the request");
        }

        if (vehicle.getPassengerCapacity() < transportRequest.getPassengerCount()) {
            throw new VehicleNotSuitableException("Vehicle passenger capacity doesn't match the request");
        }

        Trip trip = Trip.builder()
                .transportRequest(transportRequest)
                .driver(driver)
                .vehicle(driver.getVehicle())
                .build();

        transportRequest.changeStatus(TransportRequestStatus.ASSIGNED);

        return trip;
    }

    public void start() {
        if (transportRequest.getRequestStatus() != TransportRequestStatus.ASSIGNED) {
            throw new InvalidOperationException("Only trip with assigned driver can be started");
        }

        this.startedAt = OffsetDateTime.now();

        transportRequest.changeStatus(TransportRequestStatus.IN_PROGRESS);
    }

    public void complete(String completionComment, VehicleCondition vehicleConditionAfter) {
        if (transportRequest.getRequestStatus() != TransportRequestStatus.IN_PROGRESS) {
            throw new InvalidOperationException("Only trip in progress can be completed");
        }

        this.completedAt = OffsetDateTime.now();
        this.completionComment = completionComment;
        this.vehicleConditionAfter = vehicleConditionAfter;

        transportRequest.changeStatus(TransportRequestStatus.COMPLETED);
    }
}
