package com.dpimkin.geotracker.util;

import ch.hsr.geohash.GeoHash;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class GeohashCodec {
    private static final int PRECISION = 12;

    public String encode(double latitude, double longitude) {
        return GeoHash.withCharacterPrecision(latitude, longitude, PRECISION).toBase32();

    }

    public List<String> adjacent(double latitude, double longitude) {
        GeoHash geoHash = GeoHash.withCharacterPrecision(latitude, longitude, PRECISION);
        return Arrays.stream(geoHash.getAdjacent())
                .map(GeoHash::toBase32)
                .toList();
    }

}
