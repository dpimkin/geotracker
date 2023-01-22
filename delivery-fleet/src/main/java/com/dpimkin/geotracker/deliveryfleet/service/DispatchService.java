package com.dpimkin.geotracker.deliveryfleet.service;

import com.dpimkin.geotracker.deliveryfleet.model.CourierDocument;
import com.dpimkin.geotracker.deliveryfleet.model.CourierRepository;
import com.dpimkin.geotracker.deliveryfleet.model.IdempotentOperationDocument;
import com.dpimkin.geotracker.deliveryfleet.model.IdempotentOperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import om.dpimkin.geotracker.deliveryfleet.api.Disposition;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class DispatchService {

    private final CourierRepository courierRepository;
    private final IdempotentOperationRepository idempotentOperationRepository;

    public Mono<CourierDocument> updateCourierDisposition(String courierId,
                                                          String idempotencyKey,
                                                          Disposition disposition) {
        var created = Instant.now();
        return idempotentOperationRepository.save(new IdempotentOperationDocument()
                .setIdempotencyKey(idempotencyKey)
                .setCreated(created))
                .onErrorResume(DuplicateKeyException.class, e -> {
                    var message = "stalled operation with idempotency-key " + idempotencyKey;
                    log.warn(message);
                    return Mono.error(new StalledOperationException(message));
                })
                .flatMap(op -> courierRepository.findById(courierId))
                .flatMap(courierDocument -> {
                    courierDocument.setDisposition(disposition);
                    return courierRepository.save(courierDocument);
                }); // TODO
    }
}
