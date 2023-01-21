package com.dpimkin.geotracker.model;

import com.dpimkin.geotracker.model.CourierPosition;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface CourierPositionRepository {

    Mono<CourierPosition> findById(String id);
    Flux<CourierPosition> findAll();
    Mono<CourierPosition> update(CourierPosition entity);
}
