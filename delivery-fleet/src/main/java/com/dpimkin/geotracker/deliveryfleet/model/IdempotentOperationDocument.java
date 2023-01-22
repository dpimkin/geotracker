package com.dpimkin.geotracker.deliveryfleet.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Data
@Document(collection = "operation_log")
@Accessors(chain = true)
public class IdempotentOperationDocument {
    public static final String IDEMPOTENCY_KEY_FIELD = "idempotency_key";

    @Id
    private String id;

    @Field(IDEMPOTENCY_KEY_FIELD)
    private String idempotencyKey;

    private Instant created;
}
