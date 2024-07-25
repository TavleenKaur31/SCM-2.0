package com.c.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@Configuration
public class AppConfig {
	 private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	    @Value("${cloudinary.cloud.name}")
	    private String cloudName;

	    @Value("${cloudinary.api.key}")
	    private String apiKey;

	    @Value("${cloudinary.api.secret}")
	    private String apiSecret;

	    @Bean
	    public Cloudinary cloudinary() {
	        logger.info("Cloudinary Config - cloud_name: '{}', api_key: '{}', api_secret: '{}'", cloudName, apiKey, apiSecret);

	        if (cloudName == null || apiKey == null || apiSecret == null) {
	            throw new IllegalArgumentException("Cloudinary configuration properties cannot be null");
	        }

	        return new Cloudinary(ObjectUtils.asMap(
	            "cloud_name", cloudName.trim(),
	            "api_key", apiKey.trim(),
	            "api_secret", apiSecret.trim()
	        ));
	    }
	
	
}
