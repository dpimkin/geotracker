package com.dpimkin.geotracker.deliveryfleet.service;

import com.dpimkin.geotracker.deliveryfleet.model.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DispatchService {

    private final CourierRepository courierRepository;
}
