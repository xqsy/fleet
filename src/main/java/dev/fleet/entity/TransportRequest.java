package dev.fleet.entity;

import java.time.OffsetDateTime;

import dev.fleet.entity.enums.TransportRequestStatus;
import dev.fleet.entity.enums.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "transport_requests")
public class TransportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_address", nullable = false)
    private String departureAddress;

    @Column(name = "destination_address", nullable = false)
    private String destinationAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "required_vehicle_type", nullable = false)
    private VehicleType requiredVehicleType;

    @Column(name = "cargo_weight_kg", nullable = false)
    private Integer cargoWeightKg;

    @Column(name = "passenger_count", nullable = false)
    private Integer passengerCount;

    @Column(name = "request_description")
    private String requestDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false)
    private TransportRequestStatus requestStatus;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Builder
    public TransportRequest(
            String departureAddress,
            String destinationAddress,
            VehicleType requiredVehicleType,
            Integer cargoWeightKg,
            Integer passengerCount,
            String requestDescription
    ) {
        this.departureAddress = departureAddress;
        this.destinationAddress = destinationAddress;
        this.requiredVehicleType = requiredVehicleType;
        this.cargoWeightKg = cargoWeightKg;
        this.passengerCount = passengerCount;
        this.requestDescription = requestDescription;

        this.requestStatus = TransportRequestStatus.NEW;
        this.createdAt = OffsetDateTime.now();
    }

    public void update(
            String departureAddress,
            String destinationAddress,
            VehicleType requiredVehicleType,
            Integer cargoWeightKg,
            Integer passengerCount,
            String requestDescription
    ) {
        this.departureAddress = departureAddress;
        this.destinationAddress = destinationAddress;
        this.requiredVehicleType = requiredVehicleType;
        this.cargoWeightKg = cargoWeightKg;
        this.passengerCount = passengerCount;
        this.requestDescription = requestDescription;
    }

    public void changeStatus(TransportRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
