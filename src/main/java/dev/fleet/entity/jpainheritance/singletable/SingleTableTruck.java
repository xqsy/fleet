package dev.fleet.entity.jpainheritance.singletable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DiscriminatorValue("TRUCK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SingleTableTruck extends SingleTableVehicle {

    @Column(name = "load_capacity_kg")
    private Integer loadCapacityKg;

}
