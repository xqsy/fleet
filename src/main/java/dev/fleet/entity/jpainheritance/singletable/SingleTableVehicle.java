package dev.fleet.entity.jpainheritance.singletable;


import dev.fleet.entity.enums.VehicleCondition;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "st_vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class SingleTableVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_condition", nullable = false)
    private VehicleCondition vehicleCondition;

}
