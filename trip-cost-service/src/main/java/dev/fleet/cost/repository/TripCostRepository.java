package dev.fleet.cost.repository;

import dev.fleet.cost.document.TripCost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TripCostRepository extends MongoRepository<TripCost, String> {

    Optional<TripCost> findByTransportRequestId(Long transportRequestId);
}
