package com.manju.redis.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RedisConfig {


    @Bean("redisTemplate")
    RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {

		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}
    
	@Bean("redisConnectionFactory")
	RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
		standaloneConfig.setHostName("localhost");
		standaloneConfig.setPort(6379);
		return new JedisConnectionFactory(standaloneConfig);
	}
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
