
package com.incallup.backend;

import com.incallup.backend.domain.Admin;
import com.incallup.backend.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	CommandLineRunner run(){
		return args -> {

		System.out.println("Hello world from command line runner");
		};
	}
}

