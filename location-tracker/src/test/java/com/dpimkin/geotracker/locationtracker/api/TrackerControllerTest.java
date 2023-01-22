package com.dpimkin.geotracker.locationtracker.api;

import com.dpimkin.geotracker.locationtracker.model.CourierLocation;
import com.dpimkin.geotracker.locationtracker.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@ActiveProfiles("standalone-redis")
@SpringBootTest(webEnvironment = RANDOM_PORT)
class TrackerControllerTest {

    private static final int REDIS_PORT = 6379;


    @Autowired
    private WebTestClient webClient;


    @Container
    private static GenericContainer<?> REDIS = new GenericContainer<>(DockerImageName.parse("redis:5.0.3-alpine"))
            .withExposedPorts(REDIS_PORT);

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", REDIS::getContainerIpAddress);
        registry.add("spring.redis.port", () -> REDIS.getMappedPort(REDIS_PORT));
    }

    @Test
    void findCouriersNear_should() {
        updateCourierPosition("42", 42.695084, 23.324925)
                .expectStatus()
                .isAccepted();


        var actualResult= findCouriersNear(42.686202, 23.326248, 5000)
                .expectStatus()
                .isOk()
                .expectBodyList(CourierLocation.class);
        actualResult.contains(CourierLocation.of("42", Location.of(new Point(42.695084, 23.324925))));
    }



    ResponseSpec updateCourierPosition(String id, double latitude, double longitude) {
        var requestBody = new UpdatePositionRequest();
        requestBody.setLatitude(latitude);
        requestBody.setLongitude(longitude);
        return webClient.put()
                .uri(Endpoints.TRACKER_ENDPOINT + '/' + id)
                .bodyValue(requestBody)
                .exchange();
    }

    ResponseSpec findCouriersNear(double latitude, double longitude, int distance) {
        var requestBody = new FindCouriersNearRequest();
        requestBody.setLatitude(latitude);
        requestBody.setLongitude(longitude);
        requestBody.setDistanceMeters(distance);

        return webClient.post()
                .uri(Endpoints.TRACKER_ENDPOINT + "/find-near")
                .bodyValue(requestBody)
                .exchange();
    }

    ResponseSpec findCouriersWithin(double latitude, double longitude, int radius) {
        var requestBody = new FindCouriersWithinRequest();
        requestBody.setLatitude(latitude);
        requestBody.setLongitude(longitude);
        requestBody.setRadiusMeters(radius);

        return webClient.post()
                .uri(Endpoints.TRACKER_ENDPOINT + "/find-within")
                .bodyValue(requestBody)
                .exchange();
    }
}