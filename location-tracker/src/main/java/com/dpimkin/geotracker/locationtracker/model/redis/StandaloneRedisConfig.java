package com.dpimkin.geotracker.locationtracker.model.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Slf4j
@Profile("standalone-redis")
@Configuration(proxyBeanMethods = false)
public class StandaloneRedisConfig {

    @Value("${spring.redis.host:localhost}")
    String host;

    @Value("${spring.redis.port:6379}")
    int port;

    @Bean
    public LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }
}
