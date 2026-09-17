package dev.fleet.entity.jpainheritance.tableperclass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tpc_buses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TablePerClassBus extends TablePerClassVehicle {

    @Column(name = "passenger_capacity", nullable = false)
    private Integer passengerCapacity;
}
