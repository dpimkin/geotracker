package com.dpimkin.geotracker.deliveryfleet;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MongoDBContainer;

import javax.annotation.Nonnull;

public class DockerizedMongoSupport {
    private static final String MONGODB_IMAGE = "mongo:5.0.14";


    @Nonnull
    public static MongoDBContainer createMongoContainer() {
        return new MongoDBContainer(MONGODB_IMAGE).withReuse(true);
    }

    public static void initRegistryWithMongo(@Nonnull DynamicPropertyRegistry registry, @Nonnull MongoDBContainer mongo) {
        registry.add("spring.data.mongodb.uri", mongo::getReplicaSetUrl);
    }

}
