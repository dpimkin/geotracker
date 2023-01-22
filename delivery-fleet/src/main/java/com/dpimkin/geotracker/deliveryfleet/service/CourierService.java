package com.dpimkin.geotracker.deliveryfleet.service;

import com.dpimkin.geotracker.deliveryfleet.mapper.CourierMapper;
import com.dpimkin.geotracker.deliveryfleet.model.CourierDocument;
import com.dpimkin.geotracker.deliveryfleet.model.CourierRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.dpimkin.geotracker.deliveryfleet.api.CourierDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierMapper courierMapper;
    private final CourierRepository courierRepository;

    public Mono<CourierDocument> registerCourier(CourierDTO in) {
        var doc = new CourierDocument();
        doc.setDescription(in.getDescription());
        return courierRepository.save(doc);
    }

    public Mono<CourierDocument> findCourier(String id) {
        return courierRepository.findById(id);
    }

}
