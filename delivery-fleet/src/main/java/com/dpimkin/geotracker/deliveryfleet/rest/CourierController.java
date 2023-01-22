package com.dpimkin.geotracker.deliveryfleet.rest;

import com.dpimkin.geotracker.deliveryfleet.mapper.CourierMapper;
import com.dpimkin.geotracker.deliveryfleet.service.CourierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.dpimkin.geotracker.deliveryfleet.api.CourierDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static om.dpimkin.geotracker.deliveryfleet.api.Endpoints.COURIER_ENDPOINT;

@Slf4j
@RestController
@RequestMapping(path = COURIER_ENDPOINT)
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;
    private final CourierMapper courierMapper;

    @GetMapping(path = "{id}")
    Mono<ResponseEntity<CourierDTO>> findCourierById(@PathVariable("id") String id) {
        return courierService.findCourier(id)
                .map(doc -> ResponseEntity.ok(courierMapper.mapCourierDocument(doc)))
                .switchIfEmpty(Mono.defer(() -> Mono.just(ResponseEntity.notFound().build())));
    }

    @PostMapping
    Mono<CourierDTO> registerCourier(@RequestBody CourierDTO request) {
        return courierService.registerCourier(request)
                .map(courierMapper::mapCourierDocument);
    }
}
