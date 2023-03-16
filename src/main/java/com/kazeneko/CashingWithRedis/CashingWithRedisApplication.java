package com.kazeneko.CashingWithRedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.File;

@EnableCaching
@SpringBootApplication
public class CashingWithRedisApplication {
	public static void main(String[] args) {
		SpringApplication.run(CashingWithRedisApplication.class, args);
	}
}
