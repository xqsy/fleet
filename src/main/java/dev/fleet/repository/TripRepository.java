package dev.fleet.repository;

import dev.fleet.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, PK> {
}
