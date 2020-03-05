package com.carousell.marketplace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.carousell.marketplace"})
public class MarketplaceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(MarketplaceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
