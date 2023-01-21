package com.dpimkin.geotracker.api;

import com.dpimkin.geotracker.model.CourierPosition;
import com.dpimkin.geotracker.service.TrackerService;
import com.dpimkin.geotracker.util.GeohashCodec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("v1/tracker")
@RequiredArgsConstructor
public class TrackerController {

    private final TrackerService trackerService;

    @GetMapping(path = "{id}")
    Mono<CourierPosition> findBy(@PathVariable("id") String id) {
        return trackerService.findCourierPositionById(id);
    }

    @PutMapping(path = "{id}")
    Mono<ResponseEntity<String>> updateCourierPosition(@PathVariable("id") String id,
                                                       @RequestBody UpdatePositionRequest request) {

        var geohash = GeohashCodec.encode(request.getLatitude(), request.getLongitude());
        log.info("lat {} long {} geohash {}", request.getLatitude(), request.getLongitude(), geohash);
        var courierPos = new CourierPosition(id, geohash, request.getLongitude(), request.getLatitude());
        return trackerService.updateCourierPosition(courierPos)
                .map(entity -> ResponseEntity.ok(""));
    }

}
