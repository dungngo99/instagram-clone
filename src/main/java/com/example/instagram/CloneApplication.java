package com.example.instagram;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class CloneApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloneApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Let's inspect the beans provided by Spring boot");
			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			// Arrays.stream(beanNames).forEach(System.out::println);
		};
	}
}
