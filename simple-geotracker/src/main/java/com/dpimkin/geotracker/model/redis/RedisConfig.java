package com.dpimkin.geotracker.model.redis;

import com.dpimkin.geotracker.model.CourierPosition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration(proxyBeanMethods = false)
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    ReactiveRedisOperations<String, CourierPosition> courierPositionOps(LettuceConnectionFactory connectionFactory) {
        RedisSerializationContext<String, CourierPosition> serializationContext = RedisSerializationContext.<String, CourierPosition>newSerializationContext(new StringRedisSerializer())
                .key(new StringRedisSerializer())
                .value(new GenericToStringSerializer<>(CourierPosition.class))
                .hashKey(new StringRedisSerializer())
                .hashValue(new GenericJackson2JsonRedisSerializer())
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }
}
