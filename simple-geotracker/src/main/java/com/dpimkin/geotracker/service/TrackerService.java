package com.dpimkin.geotracker.service;

import com.dpimkin.geotracker.model.CourierPosition;
import com.dpimkin.geotracker.model.CourierPositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackerService {
    private final CourierPositionRepository courierPositionRepository;

    public Mono<CourierPosition> findCourierPositionById(String id) {
        return courierPositionRepository.findById(id);
    }

    public Mono<CourierPosition> updateCourierPosition(CourierPosition request) {
        return courierPositionRepository.update(request);
    }

    public Flux<CourierPosition> findAll() {
        return courierPositionRepository.findAll();
    }
}
