package com.manju.redis.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RedisService {
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	String url = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current_weather=true&hourly=temperature_2m,relativehumidity_2m,windspeed_10m";
	
	

	public String getWeatherReport() {
		String response = null;
		
		if(redisTemplate.opsForValue().get("weather_1") != null) {
			System.out.println("Fetching weather report from Redis Cache!!!");
			response = (String) redisTemplate.opsForValue().get("weather_1");
			return response;
		}
		
		System.out.println("Fetching weather report from Weather API !!!");	
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);			
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,String.class);
		response = responseEntity.getBody();		
		redisTemplate.opsForValue().set("weather_1", response,2,TimeUnit.MINUTES);		
		return response; 
				
	}
	
	

}
