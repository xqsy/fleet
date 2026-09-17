package dev.fleet.entity.jpainheritance.singletable;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DiscriminatorValue("BUS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SingleTableBus extends SingleTableVehicle {

    @Column(name = "passenger_capacity")
    private Integer passengerCapacity;

}
