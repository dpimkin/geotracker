package com.dpimkin.geotracker.locationtracker.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UpdatePositionRequest {
    Double latitude;
    Double longitude;
}
