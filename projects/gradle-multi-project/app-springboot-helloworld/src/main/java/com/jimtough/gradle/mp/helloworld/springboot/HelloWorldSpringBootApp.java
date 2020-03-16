package com.jimtough.gradle.mp.helloworld.springboot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloWorldSpringBootApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldSpringBootApp.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldSpringBootApp.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(ApplicationContext ctx) {
		return (aa) -> {
			LOGGER.info("My ApplicationRunner was invoked | {}", Arrays.toString(aa.getSourceArgs()));
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {
			LOGGER.info("My CommandLineRunner was invoked | {}", Arrays.toString(args));
			LOGGER.info("Open this URL in a browser if running locally: http://localhost:8080/");
		};
	}

}
