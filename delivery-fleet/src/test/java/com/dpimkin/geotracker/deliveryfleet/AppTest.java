package com.dpimkin.geotracker.deliveryfleet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(properties = "logging.level.org.mongodb.driver=TRACE")
class AppTest {

    @Container
    private static final MongoDBContainer MONGODB = DockerizedMongoSupport.createMongoContainer();

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        DockerizedMongoSupport.initRegistryWithMongo(registry, MONGODB);
    }

    @Test
    void contextLoads() {

    }


}