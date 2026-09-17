package dev.fleet.entity.jpainheritance.joined;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "j_trucks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinedTruck extends JoinedVehicle {

    @Column(name = "load_capacity_kg", nullable = false)
    private Integer loadCapacityKg;

}
