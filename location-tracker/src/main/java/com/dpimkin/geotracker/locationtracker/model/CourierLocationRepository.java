package com.dpimkin.geotracker.locationtracker.model;

import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CourierLocationRepository extends CrudRepository<CourierLocation, String> {
    List<CourierLocation> findByLocationPointNear(Point point, Distance distance);
    List<CourierLocation> findByLocationWithin(Circle circle);
}
