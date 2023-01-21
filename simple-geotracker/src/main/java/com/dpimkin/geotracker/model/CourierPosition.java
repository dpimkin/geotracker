package com.dpimkin.geotracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@RedisHash("courier-pos")
@NoArgsConstructor
@AllArgsConstructor
public class CourierPosition implements Serializable {

    @Id
    private String id;

    @Indexed
    private String geoHash;

    private double longitude;

    private double latitude;
}
