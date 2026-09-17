package dev.fleet.entity.jpainheritance.tableperclass;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tpc_trucks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TablePerClassTruck extends TablePerClassVehicle {

    @Column(name = "load_capacity_kg", nullable = false)
    private Integer loadCapacityKg;
}
