package dev.fleet.entity.jpainheritance.joined;


import dev.fleet.entity.enums.VehicleCondition;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "j_vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class JoinedVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_condition", nullable = false)
    private VehicleCondition vehicleCondition;
}
