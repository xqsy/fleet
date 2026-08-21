package dev.fleet.entity;

import dev.fleet.entity.enums.VehicleCondition;
import dev.fleet.entity.enums.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @Column(name = "load_capacity_kg", nullable = false)
    private Integer loadCapacityKg;

    @Column(name = "passenger_capacity", nullable = false)
    private Integer passengerCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_condition", nullable = false)
    private VehicleCondition vehicleCondition;

    @Builder
    public Vehicle(
            String registrationNumber,
            VehicleType vehicleType,
            Integer loadCapacityKg,
            Integer passengerCapacity,
            VehicleCondition vehicleCondition
    ) {
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.loadCapacityKg = loadCapacityKg;
        this.passengerCapacity = passengerCapacity;
        this.vehicleCondition = vehicleCondition;
    }

    public void update(
            String registrationNumber,
            VehicleType vehicleType,
            Integer loadCapacityKg,
            Integer passengerCapacity,
            VehicleCondition vehicleCondition
    ) {
        this.registrationNumber = registrationNumber;
        this.vehicleType = vehicleType;
        this.loadCapacityKg = loadCapacityKg;
        this.passengerCapacity = passengerCapacity;
        this.vehicleCondition = vehicleCondition;
    }
}