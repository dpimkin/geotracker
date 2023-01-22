package com.dpimkin.geotracker.locationtracker.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FindCouriersNearRequest {
    Double latitude;
    Double longitude;
    Integer distanceMeters;
}
