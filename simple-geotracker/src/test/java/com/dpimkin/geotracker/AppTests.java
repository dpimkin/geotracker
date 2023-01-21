package com.dpimkin.geotracker;

//import com.redis.testcontainers.RedisContainer;
import com.dpimkin.geotracker.api.UpdatePositionRequest;
import com.dpimkin.geotracker.model.CourierPosition;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Testcontainers
@ActiveProfiles("standalone-redis")
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = App.class)
class AppTests {
    private static final int REDIS_PORT = 6379;

    @Container
    private static GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
            .withExposedPorts(REDIS_PORT);

    @Autowired
    private WebTestClient webClient;

    /**
     * redis://localhost:6379
     * 6379
     * @param registry
     */

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {

        registry.add("redis.host", () -> {
            return redis.getContainerIpAddress();

        });

        registry.add("redis.port", () -> {
            return redis.getMappedPort(REDIS_PORT);

        });
    }

    @Test
    void contextLoads() {
    }

    @Test
    void createAndFetchPosition() {
        final var id = "42";
        final var latitude = 42.695084;
        final var longitude = 23.324925;


        updateCourierPosition(id, UpdatePositionRequest.of(latitude, longitude))
                .expectStatus()
                .isOk();

        var result = getCourierPosition(id)
                .expectStatus()
                .isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody(CourierPosition.class)
                .returnResult()
                .getResponseBody();

        assertAll("incorrect courier position info",
                () -> assertEquals(id, result.getId()),
                () -> assertEquals(latitude, result.getLatitude()),
                () -> assertEquals(longitude, result.getLongitude()));
    }

    private ResponseSpec updateCourierPosition(String id, UpdatePositionRequest payload) {
        return webClient.put()
                .uri("v1/tracker/" + id)
                .bodyValue(payload)
                .exchange();
    }

    private ResponseSpec getCourierPosition(String id) {
        return webClient.get()
                .uri("v1/tracker/" + id)
                .exchange();
    }

}
