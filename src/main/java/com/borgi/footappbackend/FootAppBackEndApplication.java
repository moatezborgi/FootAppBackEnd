package com.borgi.footappbackend;

import com.borgi.footappbackend.configuration.RSAKeyRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ConfigurationPropertiesScan
@EnableCaching
@EnableConfigurationProperties(RSAKeyRecord.class)
@SpringBootApplication
public class FootAppBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootAppBackEndApplication.class, args);
	}

}
