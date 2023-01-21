package com.dpimkin.geotracker.model.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Profile("standalone-redis")
@Configuration
public class StandaloneRedisProfile {

    @Bean
    public LettuceConnectionFactory connectionFactory(@Value("${redis.host:localhost}") String host,
                                                      @Value("${redis.port:6379}") int port) {
        return new LettuceConnectionFactory(host, port);
    }

}
