package dev.fleet.repository;

import dev.fleet.entity.TransportRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRequestRepository extends JpaRepository<TransportRequest, Long> {
}
