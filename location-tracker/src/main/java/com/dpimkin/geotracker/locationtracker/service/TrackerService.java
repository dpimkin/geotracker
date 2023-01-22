package com.dpimkin.geotracker.locationtracker.service;


import com.dpimkin.geotracker.locationtracker.model.CourierLocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Distance;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackerService {
    private final CourierLocationRepository courierLocationRepository;


//    public void test() {
//
//        //Distance distance = new Distance(100, Metrics.METERS);
//
//        //courierLocationRepository.findByCurrentLocationPointNear()
//    }
}
