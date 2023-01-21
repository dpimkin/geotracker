package com.dpimkin.geotracker.model.redis;

import com.dpimkin.geotracker.model.CourierPosition;
import com.dpimkin.geotracker.model.CourierPositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.UUID;

import static com.dpimkin.geotracker.util.Optionals.ofNullableOrEmpty;

@Slf4j
@Repository

public class RedisCourierPositionRepository implements CourierPositionRepository {
    private final String KEY = "pos";

    private final ReactiveRedisOperations<String, CourierPosition> courierPositionOps;
    private final ReactiveHashOperations<String, String, CourierPosition> courierPositionHashOps;

    public RedisCourierPositionRepository(ReactiveRedisOperations<String, CourierPosition> courierPositionOps) {
        this.courierPositionOps = courierPositionOps;
        this.courierPositionHashOps = courierPositionOps.opsForHash();
    }

    @Override
    public Mono<CourierPosition> findById(String id) {
        return courierPositionHashOps.get(KEY, id);
    }

    @Override
    public Flux<CourierPosition> findAll() {
        return courierPositionHashOps.values(KEY);
    }

    @Override
    public Mono<CourierPosition> update(CourierPosition entity) {
        return ofNullableOrEmpty(entity.getId())
                .map(id -> courierPositionHashOps.put(KEY, entity.getId(), entity)
                        .map(isSaved -> entity))
                .orElseGet(() -> {
                    final var userId = UUID.randomUUID().toString();
                    entity.setId(userId);
                    return courierPositionHashOps.put(KEY, userId, entity)
                            .map(isSaved -> entity);

        });
    }

    public Flux<CourierPosition> findByGeohashSet(Set<String> geohashSet) {
        return courierPositionHashOps.values(KEY)
                .filter(cp -> geohashSet.contains(cp.getGeoHash()));
    }
}
