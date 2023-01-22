package com.dpimkin.geotracker.locationtracker.service;


import com.dpimkin.geotracker.locationtracker.api.FindCouriersNearRequest;
import com.dpimkin.geotracker.locationtracker.api.FindCouriersWithinRequest;
import com.dpimkin.geotracker.locationtracker.model.CourierLocation;
import com.dpimkin.geotracker.locationtracker.model.CourierLocationRepository;
import com.dpimkin.geotracker.locationtracker.model.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.domain.geo.Metrics;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackerService {
    private final CourierLocationRepository courierLocationRepository;

    public void updateCourierPosition(@Nonnull String id, double latitude, double longitude) {
        var location = new Location();
        location.setPoint(new Point(latitude, longitude));
        var entity = new CourierLocation();
        entity.setId(id);
        entity.setLocation(location);
        courierLocationRepository.save(entity);
    }

    @Nonnull
    public List<CourierLocation> findCouriersNear(@Nonnull FindCouriersNearRequest request) {
        var point = new Point(request.getLatitude(), request.getLongitude());
        var distance = new Distance(request.getDistanceMeters(), Metrics.METERS);
        return courierLocationRepository.findByLocationPointNear(point, distance);
    }

    @Nonnull
    public List<CourierLocation> findCouriersWithin(@Nonnull FindCouriersWithinRequest request) {
        var point = new Point(request.getLatitude(), request.getLongitude());
        var distance = new Distance(request.getRadiusMeters(), Metrics.METERS);
        var circle = new Circle(point, distance);
        return courierLocationRepository.findByLocationWithin(circle);
    }
}
