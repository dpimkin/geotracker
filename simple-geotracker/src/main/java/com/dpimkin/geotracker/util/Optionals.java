package com.dpimkin.geotracker.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class Optionals {

    public static Optional<String> ofNullableOrEmpty(String str) {
        return Optional.ofNullable(str).filter(value -> !value.isEmpty());
    }
}
