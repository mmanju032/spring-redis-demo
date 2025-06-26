package com.manju.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringRedisDemoApplicationTests {
	
	@Autowired
	RedisTemplate<Object,Object> template;

	@Test
	void testRedisConnection() {
		
		template.opsForValue().set("India", "Delhi");
		template.opsForValue().set("Usa", "Washington");
		
		Object object = template.opsForValue().get("India");		
		System.out.println(object);
		
	}

}
