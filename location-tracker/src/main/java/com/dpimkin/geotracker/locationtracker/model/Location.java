package com.dpimkin.geotracker.locationtracker.model;

import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.index.GeoIndexed;


@Data
public class Location {
    @GeoIndexed
    Point point;
}
