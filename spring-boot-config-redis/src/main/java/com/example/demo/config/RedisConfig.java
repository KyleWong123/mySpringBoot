package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.jws.Oneway;

@Configuration
public class RedisConfig {
    private RedisConnectionFactory connectionFactory = null;

    @Bean(name = "RedisConnectionFactory")
    public RedisConnectionFactory initRedisConnectionFactory() {
        if (this.connectionFactory != null) {
            return this.connectionFactory;
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(30);
        poolConfig.setMaxTotal(50);
        poolConfig.setMaxWaitMillis(2000);
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        RedisStandaloneConfiguration redisStandaloneConfiguration = connectionFactory.getStandaloneConfiguration();
        connectionFactory.setHostName("127.0.0.1");
        connectionFactory.setPort(6379);
        connectionFactory.setPassword("");
        this.connectionFactory = connectionFactory;
        return connectionFactory;
    }
    @Bean(name = "RedisTemplate")
    public RedisTemplate<Object, Object> initRedisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        RedisSerializer redisSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.setConnectionFactory(initRedisConnectionFactory());
        return redisTemplate;
    }
}
