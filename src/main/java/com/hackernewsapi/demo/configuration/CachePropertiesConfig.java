package com.hackernewsapi.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@EnableConfigurationProperties
public class CachePropertiesConfig {

	@Value("${cache.expire-time}")
	private int expireTime;;

}
