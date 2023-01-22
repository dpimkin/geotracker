package com.dpimkin.geotracker.deliveryfleet.model.mongo;

import com.dpimkin.geotracker.deliveryfleet.model.IdempotentOperationDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Slf4j
@Component
@RequiredArgsConstructor
public class MongoPreconditions implements ApplicationListener<ApplicationReadyEvent> {

    private final ReactiveMongoOperations mongoOps;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ensureUniqueIdempotencyKey().subscribe();
    }



    Mono<String> ensureUniqueIdempotencyKey() {
        log.info("ensureing unique idempotency key");
        return mongoOps.indexOps(IdempotentOperationDocument.class)
                .ensureIndex(new Index().named("idempotency_key_unique")
                        .on(IdempotentOperationDocument.IDEMPOTENCY_KEY_FIELD, ASC)
                        .unique());
    }

}
