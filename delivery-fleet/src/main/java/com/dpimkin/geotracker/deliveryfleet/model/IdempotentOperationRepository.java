package com.dpimkin.geotracker.deliveryfleet.model;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdempotentOperationRepository extends ReactiveCrudRepository<IdempotentOperationDocument, String> {
}
