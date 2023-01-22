package com.dpimkin.geotracker.locationtracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("courier_location")
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CourierLocation {
    @Id
    String id;

    Location location;
}
