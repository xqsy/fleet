package dev.fleet.entity.jpainheritance.tableperclass;

import dev.fleet.entity.enums.VehicleCondition;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TablePerClassVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq_gen")
    @SequenceGenerator(name = "vehicle_seq_gen", sequenceName = "vehicle_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_condition", nullable = false)
    private VehicleCondition vehicleCondition;
}
