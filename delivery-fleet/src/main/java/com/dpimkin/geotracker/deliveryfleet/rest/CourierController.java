package com.dpimkin.geotracker.deliveryfleet.rest;

import com.dpimkin.geotracker.deliveryfleet.service.CourierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.dpimkin.geotracker.deliveryfleet.api.CourierDTO;
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

    @PostMapping
    Mono<CourierDTO> registerCourier(@RequestBody CourierDTO request) {
        return Mono.just(request);
    }

}
