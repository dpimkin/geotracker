package com.dpimkin.geotracker.locationtracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("courier_location")
@Data
public class CourierLocation {
    @Id
    String id;

    Location location;
}
