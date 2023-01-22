package com.dpimkin.geotracker.deliveryfleet.rest;

import com.dpimkin.geotracker.deliveryfleet.service.DispatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static om.dpimkin.geotracker.deliveryfleet.api.Endpoints.DISPATCH_ENDPOINT;

@Slf4j
@RestController
@RequestMapping(path = DISPATCH_ENDPOINT)
@RequiredArgsConstructor
public class DispatchController {

    private final DispatchService dispatchService;




}
