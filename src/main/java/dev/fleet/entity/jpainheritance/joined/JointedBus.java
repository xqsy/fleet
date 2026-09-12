package dev.fleet.entity.jpainheritance.joined;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "j_buses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JointedBus extends JoinedVehicle {

    @Column(name = "passenger_capacity", nullable = false)
    private Integer passengerCapacity;

}
