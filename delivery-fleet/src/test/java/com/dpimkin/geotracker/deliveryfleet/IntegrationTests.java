package com.dpimkin.geotracker.deliveryfleet;

import om.dpimkin.geotracker.deliveryfleet.api.Disposition;
import om.dpimkin.geotracker.deliveryfleet.api.Endpoints;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static om.dpimkin.geotracker.deliveryfleet.api.Headers.IDEMPOTENCY_KEY_HEADER;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = "logging.level.org.mongodb.driver=TRACE")
class IntegrationTests {

    @Container
    private static final MongoDBContainer MONGODB = DockerizedMongoSupport.createMongoContainer();

    @Autowired
    private WebTestClient webClient;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        DockerizedMongoSupport.initRegistryWithMongo(registry, MONGODB);
    }

    @Test
    void findCourier_shouldReturnNotFound() {
        findCourier("73").expectStatus()
                .isNotFound();


    }


    @NullSource
    @ValueSource(strings = {""})
    @ParameterizedTest
    void updateCourierDisposition_shouldReturnBadRequestWithInvalidId(String id) {
        updateCourierDisposition(id, "", new Disposition());
    }

    @NullSource
    @ValueSource(strings = {""})
    @ParameterizedTest
    void updateCourierDisposition_shouldReturnBadRequestWithInvalidIdempotencyKey(String idempotencyKey) {
        updateCourierDisposition("42", idempotencyKey, new Disposition());
    }

    private ResponseSpec findCourier(String id) {
        return webClient.get()
                .uri(Endpoints.COURIER_ENDPOINT + '/' + id)
                .exchange();
    }

    private ResponseSpec updateCourierDisposition(String courierId, String idempotencyKey, Disposition disposition) {
        return webClient.put()
                .uri(Endpoints.DISPATCH_ENDPOINT + '/' + courierId)
                .header(IDEMPOTENCY_KEY_HEADER, idempotencyKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(disposition)
                .exchange();
    }
}