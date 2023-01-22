package com.dpimkin.geotracker.deliveryfleet.rest;

import com.dpimkin.geotracker.deliveryfleet.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.dpimkin.geotracker.deliveryfleet.api.Disposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static om.dpimkin.geotracker.deliveryfleet.api.Endpoints.DISPATCH_ENDPOINT;
import static om.dpimkin.geotracker.deliveryfleet.api.Headers.IDEMPOTENCY_KEY_HEADER;

@Slf4j
@RestController
@RequestMapping(path = DISPATCH_ENDPOINT)
@RequiredArgsConstructor
public class DispatchController {

    private final DispatchService dispatchService;


    @PutMapping(path = "{courierId}")
    Mono<ResponseEntity<?>> updateCourierDisposition(@Valid @NotBlank @PathVariable String courierId,
                                                     @Valid @NotBlank @RequestHeader(IDEMPOTENCY_KEY_HEADER) String idempotencyKey,
                                                     @Valid @RequestBody Disposition body) {
        return Mono.just(ResponseEntity.accepted().build());

    }



}
