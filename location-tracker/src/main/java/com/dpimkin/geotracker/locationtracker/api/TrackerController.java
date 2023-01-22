package com.dpimkin.geotracker.locationtracker.api;


import com.dpimkin.geotracker.locationtracker.model.CourierLocation;
import com.dpimkin.geotracker.locationtracker.model.CourierLocationRepository;
import com.dpimkin.geotracker.locationtracker.model.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.StreamSupport;

import static com.dpimkin.geotracker.locationtracker.api.Endpoints.TRACKER_ENDPOINT;

@Slf4j
@RestController
@RequestMapping(path = TRACKER_ENDPOINT)
@RequiredArgsConstructor
public class TrackerController {

    private final CourierLocationRepository courierLocationRepository;


    @GetMapping
    List<CourierLocation> findAll() {
        return StreamSupport.stream(courierLocationRepository.findAll().spliterator(), false)
                .toList();
    }

    @PutMapping(path = "{id}")
    ResponseEntity<String> updateCourierPosition(@PathVariable("id") String id,
                                                 @RequestBody UpdatePositionRequest request) {
        var location = new Location();
        location.setPoint(new Point(request.getLatitude(), request.getLongitude()));

        var entity = new CourierLocation();
        entity.setId(id);
        entity.setLocation(location);

        courierLocationRepository.save(entity);


        return ResponseEntity.ok().build();
    }


}
