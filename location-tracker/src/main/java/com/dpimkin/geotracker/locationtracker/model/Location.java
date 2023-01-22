package com.dpimkin.geotracker.locationtracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.index.GeoIndexed;


@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Location {
    @GeoIndexed
    Point point;
}
