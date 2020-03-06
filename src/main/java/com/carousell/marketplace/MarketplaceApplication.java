package com.carousell.marketplace;

import lombok.extern.slf4j.Slf4j;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

@Slf4j
@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.carousell.marketplace"})
public class MarketplaceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		println(getColored("Starting the Application...", 2));
		SpringApplication.run(MarketplaceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	public static String getColored(String message, int color) {
		return (new AttributedStringBuilder()).append(message, AttributedStyle.DEFAULT.foreground(color)).toAnsi();
	}

}
