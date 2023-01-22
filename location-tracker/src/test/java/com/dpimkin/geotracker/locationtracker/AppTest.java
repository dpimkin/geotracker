package com.dpimkin.geotracker.locationtracker;

//
//import com.dpimkin.geotracker.locationtracker.api.UpdatePositionRequest;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.GenericContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//import org.testcontainers.utility.DockerImageName;
//
//
//import javax.annotation.Nonnull;
//import java.net.URI;
//
//import static com.dpimkin.geotracker.locationtracker.api.Endpoints.TRACKER_ENDPOINT;
//import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
//import static org.springframework.http.HttpStatus.ACCEPTED;
//
//@Testcontainers
//@ActiveProfiles("standalone-redis")
//@SpringBootTest(webEnvironment = RANDOM_PORT)
//class AppTest {
//
//    private static final int REDIS_PORT = 6379;
//
//    @Container
//    private static GenericContainer<?> REDIS = new GenericContainer<>(DockerImageName.parse("redis:5.0.3-alpine"))
//            .withExposedPorts(REDIS_PORT);
//
//    @LocalServerPort
//    private int localServerPort;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//
//    @DynamicPropertySource
//    static void setProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.redis.host", REDIS::getContainerIpAddress);
//        registry.add("spring.redis.port", () -> REDIS.getMappedPort(REDIS_PORT));
//    }
//
//
//    @Test
//    void contextLoads() {
//    }
//
//
//    @Test
//    void test() throws Exception {
//        {
//            Assertions.assertEquals(updateCourierLocation("42", 42.695084, 23.324925), ACCEPTED);
//        }
//
//
//
//        ResponseEntity<String> response = testRestTemplate.
//                getForEntity(uri(TRACKER_ENDPOINT), String.class);
//
//
//
//
//        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
//    }
//
//
//
//
//    private String uri(String postfix) {
//        return "http://localhost:"+localServerPort + '/' + postfix;
//    }
//
//
//
//    private HttpStatus updateCourierLocation(@Nonnull String id, double latitude, double longitude) throws Exception {
//        var payload = new UpdatePositionRequest();
//        payload.setLatitude(latitude);
//        payload.setLongitude(longitude);
//        var json = objectMapper.writeValueAsString(payload);
//
//        var url = uri(TRACKER_ENDPOINT + '/' + id);
//
//        final HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
//        headers.add("Accept", "*/*");
//
//
//        final HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
//        return testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class).getStatusCode();
//    }
//
//
//
//}