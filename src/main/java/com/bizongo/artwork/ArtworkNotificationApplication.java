package com.bizongo.artwork;

import com.bizongo.artwork.security.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class ArtworkNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtworkNotificationApplication.class, args);
	}

	@Bean
	public JwtConfig jwtConfig() {
		return new JwtConfig();
	}
}
