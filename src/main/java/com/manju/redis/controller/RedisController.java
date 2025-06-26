package com.manju.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manju.redis.service.RedisService;

@RestController
public class RedisController {
	
	@Autowired
	private RedisService service;		
	
	@GetMapping("/getWeather")
	public ResponseEntity<String> getWeatherReport() {		
		String weatherReport = service.getWeatherReport();
		return new ResponseEntity<String>(weatherReport,HttpStatus.OK);		
	}

}
