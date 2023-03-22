package com.hackernewsapi.demo.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfiguration {

	@Autowired
	CachePropertiesConfig cacheProperties;

	@Bean
	Caffeine<Object, Object> cacheConfig() {
		return Caffeine.newBuilder().expireAfterWrite(cacheProperties.getExpireTime(), TimeUnit.SECONDS);
	}

	@Bean
	CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
		CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
		caffeineCacheManager.setCaffeine(cacheConfig());
		return caffeineCacheManager;

	}
}